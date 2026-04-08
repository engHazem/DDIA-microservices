package com.example.trendingmoviesservice;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
public class TrendingServiceImpl extends TrendingServiceGrpc.TrendingServiceImplBase {

    private final RatingsClient ratingsClient;

    public TrendingServiceImpl(RatingsClient ratingsClient) {
        this.ratingsClient = ratingsClient;
    }

    @Override
    public void getTopMovies(Empty request, StreamObserver<MovieList> responseObserver) {

        List<String> movieIds = ratingsClient.getTopMovies();

        MovieList.Builder response = MovieList.newBuilder();

        for (String id : movieIds) {
            response.addMovies(Movie.newBuilder().setMovieId(id).build());
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}