package com.simplecode.authentication.service.controller;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.simplecode.authentication.service.dto.ErrorResponse;
import com.simplecode.authentication.service.exception.ApiError;
import com.simplecode.authentication.service.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUncaughtError(Exception e) {
        log.error("Unhandled error caught", e);

        String code = "GENERAL_ERROR";
        String message = "Internal service error";
        int status = 500;

        if (e instanceof ApiException) {
            ApiError apiError = ((ApiException) e).getApiError();
            status = apiError.getStatusCode();
            code = apiError.getErrorCodeString();
            message = e.getMessage();
        } else if (e instanceof AccessDeniedException) {
            code = "FORBIDDEN";
            message = "Access denied";
            status = 403;
        } else if (e instanceof UsernameNotFoundException) {
            code = "UNAUTHORIZED";
            message = e.getMessage();
            status = 401;
        } else if (e instanceof HttpMessageNotReadableException) {
            code = "BAD_REQUEST";
            message = e.getMessage();
            status = 400;
        } else if (e instanceof UnrecognizedPropertyException) {
            code = "BAD_REQUEST";
            message = "Unknown parameter '" + ((UnrecognizedPropertyException) e).getPropertyName()
                    + "'";
            status = 400;
        }

        var errorResponse = new ErrorResponse(code, message);
        return ResponseEntity.status(status).body(errorResponse);
    }
}
