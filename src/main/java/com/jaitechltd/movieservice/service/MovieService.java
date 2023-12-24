package com.jaitechltd.movieservice.service;

import com.jaitechltd.movieservice.model.Movie;

import java.util.List;

public interface MovieService {

    Movie createMovie(Movie movie);

    Movie getMovie(Integer movieId);

    void deleteByMovieId(Integer movieId);

    List<Movie> getAllMovies();
}