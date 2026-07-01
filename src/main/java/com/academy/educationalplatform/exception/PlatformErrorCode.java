package com.academy.educationalplatform.exception;

import org.springframework.http.HttpStatus;

public enum PlatformErrorCode {
    USER_EMAIL_EXISTS(HttpStatus.CONFLICT, "User with this email already exists: %s"),
    USER_PHONE_EXISTS(HttpStatus.CONFLICT, "User with this phone already exists: %s"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found: %s"),

    COURSE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Course with this name already exists: %s"),
    COURSE_NOT_FOUND(HttpStatus.NOT_FOUND, "Course not found: %s"),

    TEST_ALREADY_EXISTS(HttpStatus.CONFLICT, "Test with this name already exists: %s"),
    TEST_NOT_FOUND(HttpStatus.NOT_FOUND, "Test not found: %s"),

    MODULE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Module with this name already exists: %s"),
    MODULE_NOT_FOUND(HttpStatus.NOT_FOUND, "Module not found: %s");

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
