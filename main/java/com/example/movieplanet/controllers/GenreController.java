package com.example.movieplanet.controllers;

import com.example.movieplanet.models.Genre;
import com.example.movieplanet.responses.ApiResponse;
import com.example.movieplanet.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    // 创建或更新流派
    @PostMapping
    public ResponseEntity<ApiResponse<Genre>> saveOrUpdateGenre(@RequestBody Genre genre) {
        Genre savedGenre = genreService.saveOrUpdateGenre(genre);
        return ResponseEntity.ok(ApiResponse.success(savedGenre, "Genre saved successfully"));
    }

    // 获取所有流派
    @GetMapping
    public ResponseEntity<ApiResponse<List<Genre>>> getAllGenres() {
        List<Genre> genres = genreService.getAllGenres();
        return ResponseEntity.ok(ApiResponse.success(genres));
    }

    // 根据 ID 获取流派
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Genre>> getGenreById(@PathVariable Long id) {
        Optional<Genre> genre = genreService.getGenreById(id);
        if (genre.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(genre.get()));
        } else {
            return ResponseEntity.status(404).body(ApiResponse.error(404, "Genre not found"));
        }
    }

    // 根据名称获取流派
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<Genre>> getGenreByName(@PathVariable String name) {
        Genre genre = genreService.getGenreByName(name);
        if (genre != null) {
            return ResponseEntity.ok(ApiResponse.success(genre));
        } else {
            return ResponseEntity.status(404).body(ApiResponse.error(404, "Genre not found"));
        }
    }

    // 删除流派
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Genre deleted successfully"));
    }
}
