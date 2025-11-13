package com.dikshanta.geocoding.geocoding_service.services;

import com.dikshanta.geocoding.geocoding_service.dto.ApiResponse;
import com.dikshanta.geocoding.geocoding_service.dto.CoordinatesRequestDto;
import com.dikshanta.geocoding.geocoding_service.dto.CoordinatesResponseDto;
import com.dikshanta.geocoding.geocoding_service.exceptions.LocationNotFoundException;
import com.dikshanta.geocoding.geocoding_service.pojo.LocationResponsePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodingService {
    private final RestTemplate restTemplate;

    @Autowired
    public GeocodingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    public ApiResponse<CoordinatesResponseDto> getCoordinates(CoordinatesRequestDto requestDto) {
        String api = "https://nominatim.openstreetmap.org/search?q=City,+District+Province+Province,+Nepal&format=json&limit=1";
        api = api.replace("City", requestDto.getCity())
                .replace("District", requestDto.getDistrict())
                .replace("Province", requestDto.getProvince());
        ResponseEntity<LocationResponsePojo[]> response = restTemplate.exchange(api, HttpMethod.GET, null, LocationResponsePojo[].class);
        LocationResponsePojo[] body = response.getBody();
        if (body == null || body.length == 0) {
            throw new LocationNotFoundException("Location with the details you provide does not found");
        }
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
}
