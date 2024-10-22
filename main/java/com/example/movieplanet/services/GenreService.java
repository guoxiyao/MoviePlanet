package com.example.movieplanet.services;

import com.example.movieplanet.models.Genre;
import com.example.movieplanet.repositories.GenreRepository;
import com.example.movieplanet.responses.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    // 创建或更新流派
    public Genre saveOrUpdateGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    // 获取所有流派
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    // 根据 ID 获取流派
    public Optional<Genre> getGenreById(Long genreId) {
        return genreRepository.findById(genreId);
    }

    // 根据名称获取流派
    public Genre getGenreByName(String name) {
        return genreRepository.findByName(name);
    }

    public void deleteGenre(Long genreId) {
        // 检查流派是否存在
        if (!genreRepository.existsById(genreId)) {
            // 如果流派不存在，抛出自定义的 ResourceNotFoundException
            throw new ResourceNotFoundException("Genre not found with id: " + genreId);
        }

        // 如果流派存在，执行删除操作
        genreRepository.deleteById(genreId);
    }

}
