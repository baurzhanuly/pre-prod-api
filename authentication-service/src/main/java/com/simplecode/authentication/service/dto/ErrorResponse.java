package com.simplecode.authentication.service.dto;

import lombok.Data;

@Data
public class ErrorResponse {

    private final String serviceName = "authentication service";

    private final String errorCode;

    private final String message;

    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

}
