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
    MODULE_NOT_FOUND(HttpStatus.NOT_FOUND, "Module not found: %s"),

    LESSON_NOT_FOUND(HttpStatus.NOT_FOUND, "Lesson not found"),

    LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "Like not found"),

    ENGLISH_QUESTION_ALREADY_EXISTS(HttpStatus.CONFLICT, "Question in English test with this number already exists: %s"),
    ENGLISH_QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "Question in English test with this number not found: %s"),

    ANSWER_OPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "Answer option not found"),

    FILE_UPLOAD_FAILED(HttpStatus.NOT_FOUND, "FILE_UPLOAD_FAILED"),
    POSTER_NOT_FOUND(HttpStatus.NOT_FOUND, "Poster not found"),

    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Invalid email or password"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),
    ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "Access was denied");


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
