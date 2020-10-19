package com.example.demo.services;

import com.example.demo.models.Rating;
import com.example.demo.models.Ratings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class UserRatingInfo {

    @Autowired
    @Qualifier("testMake")
    WebClient.Builder webClient;

    @HystrixCommand(fallbackMethod = "fallBackUserRatings")
    public Ratings getRatings(String userId) {
        return webClient.
                build().
                get().
                uri("http://rating-service/rating/" + userId).retrieve().
                bodyToMono(Ratings.class).
                block();
    }

    public Ratings fallBackUserRatings(String userId) {
        return new Ratings(
                List.of(new Rating(0, userId, 0))
        );
    }
}
