package com.example.movieplanet.services;

import com.example.movieplanet.models.Movie;
import com.example.movieplanet.repositories.MovieRepository;
import com.example.movieplanet.responses.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // 分页和排序
    public Page<Movie> getAllMovies(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return movieRepository.findAll(pageable);
    }

    public Optional<Movie> getMovieById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    public Movie saveOrUpdateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new ResourceNotFoundException("Movie not found with id: " + movieId);
        }
        movieRepository.deleteById(movieId);
    }
}

