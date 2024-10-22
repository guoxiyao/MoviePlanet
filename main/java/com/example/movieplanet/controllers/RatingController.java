package com.example.movieplanet.controllers;

import com.example.movieplanet.models.Rating;
import com.example.movieplanet.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public Rating addOrUpdateRating(@RequestBody Rating rating) {
        return ratingService.addOrUpdateRating(rating);
    }

    @GetMapping("/movie/{movieId}")
    public List<Rating> getRatingsByMovieId(@PathVariable Long movieId) {
        return ratingService.getRatingsByMovieId(movieId);
    }

    @GetMapping("/user/{userId}")
    public List<Rating> getRatingsByUserId(@PathVariable Long userId) {
        return ratingService.getRatingsByUserId(userId);
    }

    @GetMapping("/user/{userId}/movie/{movieId}")
    public Rating getRatingByUserAndMovie(@PathVariable Long userId, @PathVariable Long movieId) {
        return ratingService.getRatingByUserAndMovie(userId, movieId);
    }
}
