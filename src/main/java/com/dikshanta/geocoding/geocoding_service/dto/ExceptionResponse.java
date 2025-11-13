package com.dikshanta.geocoding.geocoding_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {
    private LocalDateTime exceptionTime;
    private String exceptionClass;
    private String detailedMessage;
    private Object customValidators;

}
