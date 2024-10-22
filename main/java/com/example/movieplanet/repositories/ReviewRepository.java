package com.example.movieplanet.repositories;

import com.example.movieplanet.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 根据电影ID分页查找影评
    Page<Review> findAllByMovie_MovieId(Long movieId, Pageable pageable);

    // 根据用户ID分页查找影评
    Page<Review> findAllByUser_UserId(Long userId, Pageable pageable);
}

