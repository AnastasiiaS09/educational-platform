package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.InviteCode;
import com.academy.educationalplatform.entity.InviteCodeStatus;
import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.mapper.UserMapper;
import com.academy.educationalplatform.mapper.UserRoleMapper;
import com.academy.educationalplatform.repository.InviteCodeRepository;
import com.academy.educationalplatform.repository.UserRepository;
import com.academy.educationalplatform.repository.UserRoleRepository;
import com.academy.educationalplatform.security.JwtService;
import com.academy.educationalplatform.security.SecurityUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.FALSE;

@Service
public class AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final InviteCodeRepository inviteCodeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RedisService redisService;
    private final EmailService emailService;
    private final InviteCodeService inviteCodeService;
    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private static final int CODE_LENGTH = 6;
    private static final int CODE_BOUND = 1_000_000;
    private final SecureRandom random = new SecureRandom();

    public AuthService(UserService userService, UserRepository userRepository, UserRoleRepository userRoleRepository, InviteCodeRepository inviteCodeRepository, PasswordEncoder passwordEncoder, JwtService jwtService, RedisService redisService, EmailService emailService, InviteCodeService inviteCodeService, UserMapper userMapper, UserRoleMapper userRoleMapper) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.inviteCodeRepository = inviteCodeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.redisService = redisService;
        this.emailService = emailService;
        this.inviteCodeService = inviteCodeService;
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
    }

    public AnswerRequest registerInitiate(InviteCodeRequest request) {

        String email = normalizeEmail(request.getEmail());
        if (userRepository.existsByEmail(email)) {
            throw PlatformException.of(PlatformErrorCode.USER_EMAIL_EXISTS);
        }

        if (inviteCodeRepository.isAvailable(request.getCode()) == FALSE) {
            throw PlatformException.of(PlatformErrorCode.ACCESS_DENIED);
        }

        String resetCode = generateVerificationCode();
        redisService.storeRegistrationData(resetCode, request.getEmail(), request.getCode(), request.getUsername(), request.getPhone());
        emailService.sendVerificationEmail(request.getEmail(), resetCode);

        AnswerRequest answer = new AnswerRequest("Initiation was successful");

        return answer;
    }

    @Transactional
    public LoginResponse registerConfirm(ConfirmRequest request) {

        Map<Object, Object> registrationData = redisService.getRegistrationData(request.getVerificationCode());


        User user = new User();
        user.setUserName((String) registrationData.get("username"));
        user.setEmail((String) registrationData.get("email"));
        user.setPhone((String) registrationData.get("phone"));

        userRepository.save(user);
        /*InviteCode code = new InviteCode();
        code.setCode((String) registrationData.get("inviteCode"));
        code.setStatus(InviteCodeStatus.USED);
        inviteCodeRepository.save(code);*/
        InviteCode code = inviteCodeRepository.findByCode((String) registrationData.get("inviteCode"));
        code.setStatus(InviteCodeStatus.USED);
        inviteCodeRepository.save(code);

        //List<Role> roles = registrationData.getRoles();

        //if (roles == null || roles.isEmpty()) {
        //    roles = List.of(Role.USER);
        //}
        List<Role> roles = List.of(Role.USER);

        for (Role role : roles) {
            userRoleRepository.save(
                    userRoleMapper.toUserRole(user.getId(), role)
            );
        }



        String accessToken = jwtService.generateToken(user, roles);
        String refreshToken = jwtService.generateRefreshToken(user, roles);

        LoginResponse response = new LoginResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);

        redisService.deleteRegistrationData(request.getVerificationCode());

        return response;

    }

    public LoginResponse login(LoginRequest request) {
        String email = normalizeEmail(request.getEmail());

        User user = userRepository.findByEmail(email);

        var roles = userService.findRolesByUserId(user.getId());

        String accessToken = jwtService.generateToken(user, roles);
        String refreshToken = jwtService.generateRefreshToken(user, roles);

        LoginResponse response = new LoginResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);

        return response;
    }

    public LoginResponse refresh(String refreshToken) {

        // 1. Проверяем refresh token
        SecurityUser securityUser = jwtService.parseRefreshToken(refreshToken);


        // 2. Достаем пользователя из БД
        User user = userRepository.findById(securityUser.getId())
                .orElseThrow(() ->
                        PlatformException.of(PlatformErrorCode.USER_NOT_FOUND)
                );


        // 3. Получаем актуальные роли
        var roles = userService.findRolesByUserId(user.getId());


        // 4. Генерируем новые токены
        String newAccessToken =
                jwtService.generateToken(user, roles);

        String newRefreshToken =
                jwtService.generateRefreshToken(user, roles);


        return new LoginResponse(
                newAccessToken,
                newRefreshToken
        );
    }


    private String normalizeEmail(String email) {
        return email.trim().toLowerCase();
    }

    private String generateVerificationCode() {
        return String.format("%0" + CODE_LENGTH + "d", random.nextInt(CODE_BOUND));
    }
}
