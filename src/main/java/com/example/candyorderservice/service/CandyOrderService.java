package com.example.candyorderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;

@Service
public class CandyOrderService {

    private final RestTemplate restTemplate;


    public CandyOrderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void placeOrder()  {
        String url = "http://localhost:8081/place-order";
        ResponseEntity<String> response
                = restTemplate.getForEntity(url , String.class);
        System.out.println(LocalTime.now() +" Place new order with status code " + response.getStatusCode());
    }
}
