package com.example.movieplanet.repositories;

import com.example.movieplanet.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    // 根据流派名称查找流派
    Genre findByName(String name);
}
