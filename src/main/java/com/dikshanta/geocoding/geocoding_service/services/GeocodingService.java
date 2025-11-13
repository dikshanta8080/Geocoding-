package com.dikshanta.geocoding.geocoding_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
}
