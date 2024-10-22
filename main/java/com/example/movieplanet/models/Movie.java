package com.example.movieplanet.models;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(nullable = false)
    private String title;

    private Integer releaseYear;

    private String director;

    @Column(length = 1000)  // 提供足够的空间存储电影描述
    private String description;

    // 默认构造函数
    public Movie() {}

    // 带参数的构造函数
    public Movie(String title, Integer releaseYear, String director, String description) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.director = director;
        this.description = description;
    }

    // Getter 方法
    public Long getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public String getDescription() {
        return description;
    }

    // Setter 方法
    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

