use ratings_db;

CREATE TABLE IF NOT EXISTS ratings(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL,
    movie_id VARCHAR(100) NOT NULL,
    rating INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- insert records 
USE ratings_db;

-- Insert 100 users with random ratings
INSERT INTO ratings (user_id, movie_id, rating)
VALUES
-- -- Loop over users
-- USE ratings_db;

-- INSERT INTO ratings (user_id, movie_id, rating)
-- VALUES
-- ('user1', 'movie1', FLOOR(1 + RAND()*5)),
-- ('user1', 'movie2', FLOOR(1 + RAND()*5)),
-- ('user1', 'movie3', FLOOR(1 + RAND()*5)),
-- ('user1', 'movie4', FLOOR(1 + RAND()*5)),
-- ('user1', 'movie5', FLOOR(1 + RAND()*5)),

-- ('user2', 'movie3', FLOOR(1 + RAND()*5)),
-- ('user2', 'movie7', FLOOR(1 + RAND()*5)),
-- ('user2', 'movie2', FLOOR(1 + RAND()*5)),
-- ('user2', 'movie10', FLOOR(1 + RAND()*5)),
-- ('user2', 'movie5', FLOOR(1 + RAND()*5)),

-- -- Continue similarly up to user100
-- ('user100', 'movie1', FLOOR(1 + RAND()*5)),
-- ('user100', 'movie8', FLOOR(1 + RAND()*5)),
-- ('user100', 'movie12', FLOOR(1 + RAND()*5)),
-- ('user100', 'movie6', FLOOR(1 + RAND()*5)),
-- ('user100', 'movie19', FLOOR(1 + RAND()*5));