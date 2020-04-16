package com.santander.movie.repository;


import com.santander.movie.model.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovieRepository repository;

    @Test
    public void getAccountTest() {
        Movie expected = entityManager.persist(new Movie("star wars"));

        Optional<Movie> movieOptional = repository.findById(expected.getId());

        assertThat(movieOptional.get().getName()).isEqualTo(expected.getName());
    }
}
