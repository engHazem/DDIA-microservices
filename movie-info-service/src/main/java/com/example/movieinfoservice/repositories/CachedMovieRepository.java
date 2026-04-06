package com.example.movieinfoservice.repositories;

import com.example.movieinfoservice.models.CachedMovie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CachedMovieRepository extends MongoRepository<CachedMovie, String> {
}
