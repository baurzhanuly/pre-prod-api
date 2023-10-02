package com.simplecode.authentication.service.exception;

public interface ApiError {
    String getErrorCodeString();
    int getStatusCode();
}
