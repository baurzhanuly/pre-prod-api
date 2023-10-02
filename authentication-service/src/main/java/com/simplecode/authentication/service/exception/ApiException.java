package com.simplecode.authentication.service.exception;

public class ApiException extends RuntimeException {

    private final ApiError apiError;
    public ApiException(ApiError apiError, String message) {
        super(message);
        this.apiError = apiError;
    }

    public ApiException(ApiError apiError, String fmt, Object... args) {
        super(String.format(fmt, args));
        this.apiError = apiError;
    }

    public ApiException(ApiError apiError, int httpStatus, String message) {
        super(message);
        this.apiError = apiError;
    }

    public ApiException(ApiError apiError, int httpStatus, String fmt, Object... args) {
        super(String.format(fmt, args));
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }

}
