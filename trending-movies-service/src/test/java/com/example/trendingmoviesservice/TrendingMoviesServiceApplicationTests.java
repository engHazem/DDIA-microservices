package com.example.trendingmoviesservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
    classes = TrendingMoviesServiceApplication.class,
    properties = {
        "server.port=0",
        "grpc.server.port=0",
        "eureka.client.register-with-eureka=false",
        "eureka.client.fetch-registry=false"
    }
)
class TrendingMoviesServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}