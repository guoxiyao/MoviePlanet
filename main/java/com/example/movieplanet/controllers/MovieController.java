package com.example.movieplanet.controllers;

import com.example.movieplanet.models.Movie;
import com.example.movieplanet.responses.ApiResponse;
import com.example.movieplanet.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Movie>>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<Movie> movies = movieService.getAllMovies(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(ApiResponse.success(movies));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Movie>> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(movie -> ResponseEntity.ok(ApiResponse.success(movie)))
                .orElse(ResponseEntity.status(404).body(ApiResponse.error(404, "Movie not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Movie>> saveOrUpdateMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.saveOrUpdateMovie(movie);
        return ResponseEntity.ok(ApiResponse.success(savedMovie, "Movie saved successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Movie deleted successfully"));
    }
}
