package com.academy.educationalplatform.exception;

import org.springframework.http.HttpStatus;

public enum PlatformErrorCode {
    ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "Access was denied"),
    ANSWER_OPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "AnswerOption with this number not found"),

    COURSE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Course with this name already exists: %s"),
    COURSE_NOT_FOUND(HttpStatus.NOT_FOUND, "Course not found: %s"),

    ENGLISH_QUESTION_ALREADY_EXISTS(HttpStatus.CONFLICT, "Question in English test with this number already exists: %s"),
    ENGLISH_QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "Question in English test with this number not found: %s"),

    FILE_UPLOAD_FAILED(HttpStatus.NOT_FOUND, "FILE_UPLOAD_FAILED"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Invalid email or password"),

    INVITE_CODE_ALREADY_EXISTS(HttpStatus.CONFLICT, "This invite code already exists: %s"),
    INVITE_CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "Invite code not found: %s"),

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),
    REGISTRATION_SESSION_EXPIRED(HttpStatus.UNAUTHORIZED,"This registration session has expired: %s"),
    RESET_SESSION_EXPIRED(HttpStatus.UNAUTHORIZED,"This reset session has expired: %s"),

    LESSON_NOT_FOUND(HttpStatus.NOT_FOUND, "Lesson not found"),
    NOT_ALL_LESSONS_COMPLETED(HttpStatus.CONFLICT, "You have not completed all module's lessons yet"),

    LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "Like not found"),

    MODULE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Module with this name already exists: %s"),
    MODULE_NOT_FOUND(HttpStatus.NOT_FOUND, "Module not found: %s"),

    POSTER_NOT_FOUND(HttpStatus.NOT_FOUND, "Poster not found"),

    TEST_ALREADY_EXISTS(HttpStatus.CONFLICT, "Test with this name already exists: %s"),
    TEST_NOT_FOUND(HttpStatus.NOT_FOUND, "Test not found: %s"),

    USER_EMAIL_EXISTS(HttpStatus.CONFLICT, "User with this email already exists: %s"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found: %s"),
    USER_PHONE_EXISTS(HttpStatus.CONFLICT, "User with this phone already exists: %s");


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
