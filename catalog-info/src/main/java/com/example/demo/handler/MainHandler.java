package com.example.demo.handler;

import com.example.demo.models.Movie;
import com.example.demo.models.MovieRating;
import com.example.demo.models.MovieRatings;
import com.example.demo.models.Ratings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MainHandler {


    @Autowired
    @Qualifier("testMake")
    WebClient.Builder webClient;


    @GetMapping("/{userId}")
    public MovieRatings getUserRating(@PathVariable String userId) {
        Ratings ratingMono = Optional.ofNullable(webClient.
                build().
                get().
                uri("http://rating-service/rating/" + userId).retrieve().
                bodyToMono(Ratings.class).
                block()).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No movies had been rated yet"));

        return new MovieRatings(
                ratingMono.
                        getRatings().
                        stream().
                        map(rating -> {
                            Movie movie = webClient.build().
                                    get().
                                    uri("http://movie-service/movie/" + rating.getMovieId()).
                                    retrieve().bodyToMono(Movie.class).
                                    block();
                            return new MovieRating(
                                    movie.getTitle(),
                                    movie.getDescription(),
                                    rating.getRating());
                        }).collect(Collectors.toList()));

    }

    @GetMapping("/s")
    public Mono<Movie> getMovie() {
        return webClient.build().get().uri("http://movie-service/movie/1").retrieve().bodyToMono(Movie.class);
    }
}
