package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String response=movieService.addMovie(movie);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String response=movieService.addDirector(director);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("director") String director, @RequestParam("movie") String movie){
        String response= movieService.addMovieDirectorPair(director, movie);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String name){
        Movie movie=movieService.getMovieByName(name);
        if(movie==null){
            return new ResponseEntity(movie, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(movie, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name){
        Director director=movieService.getDirectorByName(name);
        if(director==null){
            return new ResponseEntity(director, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(director, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String name){
        List<String> movies=movieService.getMoviesByDirectorName(name);
        if(movies==null){
            return new ResponseEntity(movies, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(movies, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String> movies=movieService.findAllMovies();
        if(movies==null){
            return new ResponseEntity(movies, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(movies, HttpStatus.FOUND);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("name") String name){
        String response=movieService.deleteDirectorByName(name);
        if(response.equals("Director is not there")){
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String response=movieService.deleteAllDirectors();
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }
}
