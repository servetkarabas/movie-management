package com.santander.movie.service;

import com.santander.movie.model.Movie;
import com.santander.movie.model.MovieRequest;
import com.santander.movie.repository.MovieRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    private static Movie movie;
    private static MovieRequest movieRequest;

    private static List<Movie> expectedMovies;

    @Mock
    private MovieRepository repository;

    @InjectMocks
    private MovieService service;

    @BeforeAll
    public static void setup() {
        movieRequest = new MovieRequest();
        movie = new Movie("star");
        expectedMovies = new ArrayList<>();
        expectedMovies.add(movie);
    }

    @Test
    public void createmovieTest_Success() {
        movieRequest.setName("star");
        when(repository.save(any(Movie.class))).thenReturn(movie);

        Movie movie = service.create(movieRequest);

        assertThat(movie).isNotNull();
        assertThat(movie.getName()).isEqualTo("star");
    }


    @Test
    public void findAllTest_Success() {
        when(repository.findAll()).thenReturn(expectedMovies);

        List<Movie> movieList = Lists.newArrayList(service.findAll());

        assertThat(movieList).isNotEmpty();
        assertThat(movieList).hasSize(expectedMovies.size());
        assertThat(movieList).isEqualTo(expectedMovies);
    }


    @Test
    public void deleteMovieTest_Success() {
        when(repository.findById(1)).thenReturn(Optional.of(movie));

        boolean delete = service.delete(1);

        assertThat(delete).isEqualTo(true);
    }
}
