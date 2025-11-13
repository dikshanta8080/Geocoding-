package com.dikshanta.geocoding.geocoding_service.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoordinatesRequestDto {
    @NotBlank(message = "Please provide valid province name")
    private String province;
    @NotBlank(message = "Please provide valid district name")
    private String district;
    @NotBlank(message = "Please provide valid city name")
    private String city;
}
