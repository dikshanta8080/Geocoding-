package com.dikshanta.geocoding.geocoding_service.services;

import com.dikshanta.geocoding.geocoding_service.dto.ApiResponse;
import com.dikshanta.geocoding.geocoding_service.dto.CoordinatesRequestDto;
import com.dikshanta.geocoding.geocoding_service.dto.CoordinatesResponseDto;
import com.dikshanta.geocoding.geocoding_service.pojo.LocationResponsePojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodingService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    @Autowired
    public GeocodingService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public ApiResponse<CoordinatesResponseDto> getCoordinates(CoordinatesRequestDto requestDto) {
        String api = "https://nominatim.openstreetmap.org/search?q=City,+District+Province+Province,+Nepal&format=json&limit=1";
        api = api.replace("City", requestDto.getCity())
                .replace("District", requestDto.getDistrict())
                .replace("Province", requestDto.getProvince());
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "MyApp/1.0 (dikshantaacharya04@gmail.com)");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<LocationResponsePojo[]> response = restTemplate.exchange(api, HttpMethod.GET, entity, LocationResponsePojo[].class);
        LocationResponsePojo[] body = response.getBody();
        if (body.length > 0) {
            LocationResponsePojo weatherPojo = body[0];
            CoordinatesResponseDto responseDto = CoordinatesResponseDto.builder()
                    .lat(Double.parseDouble(weatherPojo.getLat()))
                    .lon(Double.parseDouble(weatherPojo.getLon()))
                    .display_name(weatherPojo.getDisplay_name())
                    .build();
            return ApiResponse.<CoordinatesResponseDto>builder()
                    .status(true)
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully parsed the coordinated")
                    .responseObject(responseDto)
                    .build();
        }

        return ApiResponse.<CoordinatesResponseDto>builder()
                .status(false)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("Failed to parse the the coordinated")
                .responseObject(null)
                .build();
    }
}
