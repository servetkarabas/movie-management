package com.santander.movie.service;


import com.santander.movie.exception.MovieNotFoundException;
import com.santander.movie.model.Movie;
import com.santander.movie.model.MovieRequest;
import com.santander.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;


    public Movie create(MovieRequest movieRequest) {
        Movie movie = new Movie();
        movie.setName(movieRequest.getName());
        return movieRepository.save(movie);
    }

    public boolean delete(Integer id) {
        try {
            Optional<Movie> byId = movieRepository.findById(id);
            movieRepository.delete(byId.get());
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException());
    }

}
