package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.LoginRequest;
import com.academy.educationalplatform.dto.LoginResponse;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.UserRepository;
import com.academy.educationalplatform.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
//
}
