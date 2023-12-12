package com.example.productservice.controller;

import com.example.productservice.dto.ProductResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConvertTestService {
    private final ObjectMapper objectMapper;

    public String serializeStoreDto(ProductResponse storedto) throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper.writeValueAsString(storedto);
    }

    //json문자열을 DTO로 변환
    public ProductResponse deserializeStoreDto(String value) throws JsonProcessingException {
        return objectMapper.readValue(value,ProductResponse.class);
    }
}
