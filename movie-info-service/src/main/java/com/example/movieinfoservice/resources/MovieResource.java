package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.models.CachedMovie;
import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieSummary;
import com.example.movieinfoservice.repositories.CachedMovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final CachedMovieRepository cachedMovieRepository;

    public MovieResource(RestTemplate restTemplate, CachedMovieRepository cachedMovieRepository) {
        this.restTemplate = restTemplate;
        this.cachedMovieRepository = cachedMovieRepository;
    }

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {

        // 1. Cache Lookup
        Optional<CachedMovie> cachedMovie = cachedMovieRepository.findById(movieId);

        if (cachedMovie.isPresent()) {
            // Cache Hit
            CachedMovie hit = cachedMovie.get();
            return new Movie(hit.getMovieId(), hit.getName(), hit.getDescription());
        }

        // 2. Cache Miss
        Movie movie;
        try {
            final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
            MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);
            movie = new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
        } catch (Exception e) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            movie = new Movie(movieId, "Dummy Movie " + movieId, "No description available (dummy data)");
        }

        // 3. Populate the cache
        CachedMovie toCache = new CachedMovie(movie.getMovieId(), movie.getName(), movie.getDescription());
        cachedMovieRepository.save(toCache);

        return movie;
    }
}
