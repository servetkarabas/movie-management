package com.santander.movie.controller;

import com.santander.movie.model.Movie;
import com.santander.movie.model.MovieRequest;
import com.santander.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movies")
    public Movie postMovie(@RequestBody MovieRequest movieRequest) {
        return movieService.create(movieRequest);
    }

    @GetMapping("/movies")
    public List<Movie> getMovieList() {
        return movieService.findAll();
    }

    @DeleteMapping("/movies/{id}")
    public Boolean deleteMovie(@PathVariable Integer id) {
        return movieService.delete(id);
    }


}
