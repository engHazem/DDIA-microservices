package com.moviecatalogservice.services;

import com.example.trendingmoviesservice.Empty;
import com.example.trendingmoviesservice.Movie;
import com.example.trendingmoviesservice.MovieList;
import com.example.trendingmoviesservice.TrendingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class TrendingMoviesGrpcClient {

    private final String host;
    private final int port;

    public TrendingMoviesGrpcClient(
            @Value("${trending.grpc.host:localhost}") String host,
            @Value("${trending.grpc.port:9090}") int port) {
        this.host = host;
        this.port = port;
    }

    public List<String> getTopMovieIds() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        try {
            TrendingServiceGrpc.TrendingServiceBlockingStub stub = TrendingServiceGrpc.newBlockingStub(channel)
                    .withDeadlineAfter(3, TimeUnit.SECONDS);

            MovieList response = stub.getTopMovies(Empty.newBuilder().build());
            return response.getMoviesList().stream().map(Movie::getMovieId).collect(Collectors.toList());
        } finally {
            channel.shutdownNow();
        }
    }
}
