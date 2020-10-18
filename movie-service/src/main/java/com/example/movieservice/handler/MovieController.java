package com.example.movieservice.handler;

import com.example.movieservice.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable int id) {
        return new Movie("test", "test desc");
    }
}
