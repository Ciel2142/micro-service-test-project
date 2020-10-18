package com.example.demo.models;

public class Rating {

    private int rating;
    private String userId;
    private int movieId;

    public Rating() {

    }

    public Rating(int rating, String userId, int movieId) {
        this.rating = rating;
        this.userId = userId;
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
