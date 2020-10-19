package com.example.demo.services;

import com.example.demo.models.Movie;
import com.example.demo.models.MovieRating;
import com.example.demo.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieInfoService {

    @Autowired
    @Qualifier("testMake")
    private WebClient.Builder webClient;

    @HystrixCommand(fallbackMethod = "fallBackMovieRating")
    public MovieRating getMovieRating(Rating rating) {
        return webClient.build().
                get().
                uri("http://movie-service/movie/" + rating.getMovieId()).
                retrieve().
                bodyToMono(Movie.class).
                map(m -> new MovieRating(
                        m.getTitle(),
                        m.getDescription(),
                        rating.getRating())).
                block();
    }


    public MovieRating fallBackMovieRating(Rating rating) {
        return new MovieRating("Movie not found", "", rating.getRating());
    }

}
