package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/initiate")
    public AnswerRequest registerInitiate(@Valid @RequestBody InviteCodeRequest request) {
        return authService.registerInitiate(request);
    }

    @PostMapping("/confirm")
    public LoginResponse registerConfirm(@Valid @RequestBody ConfirmRequest request) {
        return authService.registerConfirm(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }



    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok(
                authService.refresh(request.getRefreshToken())
        );
    }

    /*@PostMapping("/password/forgot")
    public ResponseEntity<ForgotPasswordInitiateResponse> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request) {
        authService.forgotPassword(request);
        ForgotPasswordInitiateResponse response = new ForgotPasswordInitiateResponse();
        response.setMessage("Successful initiate forgot password");
        return ResponseEntity.ok(response);
    }*/
}
