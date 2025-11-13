package com.dikshanta.geocoding.geocoding_service.exceptions;

import com.dikshanta.geocoding.geocoding_service.dto.ApiResponse;
import com.dikshanta.geocoding.geocoding_service.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> handleRuntimeExceptions(RuntimeException e) {
        HttpStatus httpStatus;
        httpStatus = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionTime(LocalDateTime.now())
                .exceptionClass(e.getClass().getSimpleName())
                .detailedMessage(e.getMessage())
                .customValidators(null)
                .build();
        ApiResponse<ExceptionResponse> apiResponse = ApiResponse.<ExceptionResponse>builder()
                .status(false)
                .httpStatus(httpStatus)
                .message("Exception occurred")
                .responseObject(exceptionResponse)
                .build();
        return new ResponseEntity<>(apiResponse, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        HttpStatus httpStatus;
        httpStatus = HttpStatus.BAD_REQUEST;
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, String> errorMap = fieldErrors.stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionTime(LocalDateTime.now())
                .exceptionClass(e.getClass().getSimpleName())
                .detailedMessage(e.getMessage())
                .customValidators(errorMap)
                .build();
        ApiResponse<ExceptionResponse> apiResponse = ApiResponse.<ExceptionResponse>builder()
                .status(false)
                .httpStatus(httpStatus)
                .message(exceptionResponse.getExceptionClass() + " occurred")
                .responseObject(exceptionResponse)
                .build();
        return new ResponseEntity<>(apiResponse, httpStatus);
    }
}
