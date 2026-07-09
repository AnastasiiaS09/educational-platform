package com.academy.educationalplatform.exception;

public class PlatformException extends RuntimeException {
    private final PlatformErrorCode errorCode;

    private PlatformException(PlatformErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public static PlatformException of(PlatformErrorCode errorCode, Object... args) {
        String message = args.length == 0 ? errorCode.getMessage() : errorCode.getMessage().formatted(args);
        return new PlatformException(errorCode, message);
    }
//
    public PlatformErrorCode getErrorCode() {
        return errorCode;
    }

}
