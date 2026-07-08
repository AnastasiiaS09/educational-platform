package com.academy.educationalplatform.service;


import com.academy.educationalplatform.dto.LoginResponse;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserService userService,UserRoleService userRoleService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(String email, String password) {
        User user = userService.findByEmailForLogin(email);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw PlatformException.of(PlatformErrorCode.INVALID_CREDENTIALS);
        }
        var roles = userRoleService.findUserRole(user.getId());
        return new LoginResponse(jwtService.generateToken(user, roles));
    }
}
