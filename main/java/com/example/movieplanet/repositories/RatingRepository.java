package com.example.movieplanet.repositories;

import com.example.movieplanet.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByMovie_MovieId(Long movieId);  // 根据电影ID查找评分

    List<Rating> findByUser_UserId(Long userId);  // 根据用户ID查找评分

    // 根据用户ID和电影ID查找评分
    Rating findByUser_UserIdAndMovie_MovieId(Long userId, Long movieId);
}
