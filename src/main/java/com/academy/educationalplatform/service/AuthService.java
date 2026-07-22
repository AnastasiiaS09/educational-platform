package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.LoginRequest;
import com.academy.educationalplatform.dto.LoginResponse;
import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.UserRepository;
import com.academy.educationalplatform.security.JwtService;
import com.academy.educationalplatform.security.SecurityUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PlatformException.of(PlatformErrorCode.INVALID_CREDENTIALS);
        }
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


}
