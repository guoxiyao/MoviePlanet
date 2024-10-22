package com.example.movieplanet.services;

import com.example.movieplanet.models.Review;
import com.example.movieplanet.repositories.ReviewRepository;
import com.example.movieplanet.responses.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // 保存或更新影评
    public Review saveOrUpdateReview(Review review) {
        return reviewRepository.save(review);
    }

    // 根据ID查找影评
    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    // 根据电影ID分页查找影评
    public Page<Review> getReviewsByMovieId(Long movieId, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return reviewRepository.findAllByMovie_MovieId(movieId, pageable);
    }

    // 根据用户ID分页查找影评
    public Page<Review> getReviewsByUserId(Long userId, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return reviewRepository.findAllByUser_UserId(userId, pageable);
    }

    // 根据ID删除影评
    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new ResourceNotFoundException("Review not found with id: " + reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }
}
