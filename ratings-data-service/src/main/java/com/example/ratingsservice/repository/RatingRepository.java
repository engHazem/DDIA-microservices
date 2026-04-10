package com.example.ratingsservice.repository;

import com.example.ratingsservice.models.Rating;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RatingRepository {

    private final JdbcTemplate jdbcTemplate;

    public RatingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Rating> getRatingsByUserId(String userId) {

        String sql = "SELECT movie_id, rating, id FROM ratings WHERE user_id = ?";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Rating(
                        rs.getLong("id"),
                        rs.getString("movie_id"),
                        rs.getInt("rating")
                ),
                userId
        );
    }

    public List<String> getTop10Movies() {
        String sql = """
                    SELECT movie_id
                    FROM ratings
                    GROUP BY movie_id
                    ORDER BY AVG(rating) DESC, COUNT(*) DESC
                    LIMIT 10
                """;

        List<String>  x= jdbcTemplate.query(
                sql,
                (rs, rowNum) -> rs.getString("movie_id")
        );
        System.out.println(x);
        return x;
    }
}