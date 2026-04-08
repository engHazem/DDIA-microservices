package com.example.trendingmoviesservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RatingsClient {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${DATA_SOURCE_URL}")
    private String url;
    @Cacheable("TopMovies")
    public List<String> getTopMovies() {
        System.out.println(url);
        String[] response = restTemplate.getForObject(url, String[].class);
        System.out.println(response);

        return Arrays.asList(response);
    }
}