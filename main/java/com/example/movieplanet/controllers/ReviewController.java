package com.example.movieplanet.controllers;

import com.example.movieplanet.models.Review;
import com.example.movieplanet.responses.ApiResponse;
import com.example.movieplanet.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // 创建或更新影评
    @PostMapping
    public ResponseEntity<ApiResponse<Review>> createOrUpdateReview(@RequestBody Review review) {
        Review savedReview = reviewService.saveOrUpdateReview(review);
        return ResponseEntity.ok(ApiResponse.success(savedReview, "Review saved successfully"));
    }

    // 根据ID获取影评
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Review>> getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id)
                .map(review -> ResponseEntity.ok(ApiResponse.success(review)))
                .orElse(ResponseEntity.status(404).body(ApiResponse.error(404, "Review not found")));
    }

    // 根据电影ID获取影评（带分页与排序）
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<ApiResponse<Page<Review>>> getReviewsByMovieId(
            @PathVariable Long movieId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "publishDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection
    ) {
        Page<Review> reviews = reviewService.getReviewsByMovieId(movieId, page, size, sortBy, sortDirection);
        return ResponseEntity.ok(ApiResponse.success(reviews));
    }

    // 根据用户ID获取影评（带分页与排序）
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<Page<Review>>> getReviewsByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "publishDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection
    ) {
        Page<Review> reviews = reviewService.getReviewsByUserId(userId, page, size, sortBy, sortDirection);
        return ResponseEntity.ok(ApiResponse.success(reviews));
    }

    // 根据ID删除影评
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Review deleted successfully"));
    }
}

