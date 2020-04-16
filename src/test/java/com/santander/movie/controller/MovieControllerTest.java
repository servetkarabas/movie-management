package com.santander.movie.controller;


import com.santander.movie.model.Movie;
import com.santander.movie.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;


    @Test
    public void shouldSuccesWhenPostMovie() throws Exception {
        Movie movie = new Movie();

        Mockito.when(movieService.create(any())).thenReturn(movie);

        ResultActions resultActions = mockMvc.perform(post("/api/movies").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content("{\"name\":\"star wars\"}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("name").value("star wars"));
    }

    @Test
    public void shouldGetWholeMovieListWhenGetList() throws Exception {
        mockMvc.perform(get("/api/movies").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
//                .andExpect(jsonPath("name").exists());
    }


}
