package com.example.demo.handler;

import com.example.demo.models.Movie;
import com.example.demo.models.MovieRating;
import com.example.demo.models.MovieRatings;
import com.example.demo.models.Ratings;
import com.example.demo.services.MovieInfoService;
import com.example.demo.services.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @Autowired
    UserRatingInfo userRatingInfo;

    @Autowired
    MovieInfoService movieInfoService;

    @GetMapping("/{userId}")
    public MovieRatings getUserRating(@PathVariable String userId) {
        Ratings ratings = Optional.ofNullable(
                userRatingInfo.getRatings(userId)).
                orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No movies had been rated yet"));

        return new MovieRatings(ratings.
                        getRatings().
                        stream().
                        map(movieInfoService::getMovieRating).
                        collect(Collectors.toList()));

    }
}
