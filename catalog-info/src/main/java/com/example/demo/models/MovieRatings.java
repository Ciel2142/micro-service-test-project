package com.example.demo.models;

import java.util.List;

public class MovieRatings {

    private List<MovieRating> list;

    public MovieRatings() {
    }

    public MovieRatings(List<MovieRating> list) {
        this.list = list;
    }

    public List<MovieRating> getList() {
        return list;
    }

    public void setList(List<MovieRating> list) {
        this.list = list;
    }
}
