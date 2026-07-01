package com.academy.educationalplatform.exceptions;

import org.springframework.http.HttpStatus;

public enum PlatformErrorCode {

    USER_EMAIL_EXISTS(HttpStatus.CONFLICT, "User with this email already exists: %s"),
    USER_PHONE_EXISTS(HttpStatus.CONFLICT, "User with this phone already exists: %s"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Invalid email or password"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "Access denied");

    private final HttpStatus status;
    private final String message;

    PlatformErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
