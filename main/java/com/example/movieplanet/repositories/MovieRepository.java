package com.example.movieplanet.repositories;

import com.example.movieplanet.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // 你可以在这里定义特定的查询方法，比如按标题查找电影
    Movie findByTitle(String title);
}
