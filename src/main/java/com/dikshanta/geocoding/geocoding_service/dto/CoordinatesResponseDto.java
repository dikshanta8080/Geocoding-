package com.dikshanta.geocoding.geocoding_service.dto;

import lombok.*;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesResponseDto {
    private double lat;
    private double lon;
    private String display_name;
}
