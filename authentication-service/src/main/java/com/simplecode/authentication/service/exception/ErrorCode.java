package com.simplecode.authentication.service.exception;

public enum ErrorCode implements ApiError {

    INVALID_INPUT(400),
    ALREADY_EXISTS(403),
    NOT_FOUND(404),

    USER_DISABLED(404),
    INVALID_CREDENTIALS(404),
    GENERAL_ERROR(500);

    private final int httpStatus;

    ErrorCode(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getErrorCodeString() {
        return this.name();
    }

    @Override
    public int getStatusCode() {
        return httpStatus;
    }

}
