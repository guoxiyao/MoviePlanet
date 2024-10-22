package com.example.movieplanet.services;

import com.example.movieplanet.models.Rating;
import com.example.movieplanet.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    // 添加或更新评分
    public Rating addOrUpdateRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> getRatingsByMovieId(Long movieId) {
        return ratingRepository.findByMovie_MovieId(movieId);
    }

    public List<Rating> getRatingsByUserId(Long userId) {
        return ratingRepository.findByUser_UserId(userId);
    }

    public Rating getRatingByUserAndMovie(Long userId, Long movieId) {
        return ratingRepository.findByUser_UserIdAndMovie_MovieId(userId, movieId);
    }
}
