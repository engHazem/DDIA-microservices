package com.example.trendingmoviesservice;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RatingsClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> getTopMovies() {
        String url = "http://localhost:8083/ratings/top";

        String[] response = restTemplate.getForObject(url, String[].class);

        return Arrays.asList(response);
    }
}