package com.jaitechltd.movieservice.controller;

import com.jaitechltd.movieservice.model.Movie;
import com.jaitechltd.movieservice.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    @Operation(summary = "Create a new movie", description = "Create a new movie", tags = {"movies"}, operationId = "createMovie", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Movie created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Movie already exists")})
    public Movie createMovie(@RequestBody Movie movie) {
        log.info("Create movie request received: {}", movie);
        return movieService.createMovie(movie);
    }

    @PutMapping("/{movieId}")
    @Operation(summary = "Update an existing movie, given movie id", description = "Update an existing movie, given movie id", tags = {"movies"}, operationId = "updateMovie", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Movie updated"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Movie not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405", description = "Validation exception")})
    public String updateMovie(@PathVariable Integer movieId) {
        return "Movie updated with id: " + movieId;
    }


    @GetMapping("/{movieId}")
    @Operation(summary = "Get a movie by id", description = "Get a movie by id", tags = {"movies"}, operationId = "getMovieById", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Movie found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Movie not found")})
    public ResponseEntity<Object> getMovie(@PathVariable Integer movieId) {

        final var movie = movieService.getMovie(movieId);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @DeleteMapping("/{movieId}")
    @Operation(summary = "Delete a movie by movie id", description = "Delete a movie by movie id", tags = {"movies"}, operationId = "deleteMovieById", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Movie deleted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Movie not found")})
    public ResponseEntity<Object> deleteByMovieId(@PathVariable Integer movieId) {
        movieService.deleteByMovieId(movieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @Operation(summary = "Get all movies", description = "Get all movies", tags = {"movies"}, operationId = "getAllMovies", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Movies found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Movies not found")})
    public ResponseEntity<Object> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }
}