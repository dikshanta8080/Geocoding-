package com.dikshanta.geocoding.geocoding_service.controllers;

import com.dikshanta.geocoding.geocoding_service.dto.ApiResponse;
import com.dikshanta.geocoding.geocoding_service.dto.CoordinatesRequestDto;
import com.dikshanta.geocoding.geocoding_service.dto.CoordinatesResponseDto;
import com.dikshanta.geocoding.geocoding_service.services.GeocodingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geocode")
public class GeocodeController {
    private final GeocodingService geocodingService;

    @Autowired
    public GeocodeController(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    @PostMapping("/coordinates")
    public ResponseEntity<ApiResponse<CoordinatesResponseDto>> getCoordinates(@Valid @RequestBody CoordinatesRequestDto requestDto) {
        ApiResponse<CoordinatesResponseDto> coordinates = geocodingService.getCoordinates(requestDto);
        return new ResponseEntity<>(coordinates, coordinates.getHttpStatus());

    }
}
