package com.example.ratingservice.handler;

import com.example.ratingservice.model.Rating;
import com.example.ratingservice.model.Ratings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingHandler {

    @GetMapping("/{userId}")
    public Ratings getRatings(@PathVariable String userId) {
        return new Ratings(List.of(
                new Rating(4, userId, 133),
                new Rating(3, userId, 121)));
    }
}
