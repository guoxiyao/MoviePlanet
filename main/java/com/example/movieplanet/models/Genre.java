package com.example.movieplanet.models;

import jakarta.persistence.*;

@Entity
@Table(name = "genres", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Lob
    private String description;

    // 无参构造函数
    public Genre() {}

    // 带参构造函数
    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters 和 Setters
    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
