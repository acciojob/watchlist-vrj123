package com.driver;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    Map<String, Movie> moviesDB=new HashMap<>();
    Map<String, Director> directorDB=new HashMap<>();
    Map<String, List<String>> directorMovieDB=new HashMap<>();

    public String addMovie(Movie movie){
        String name=movie.getName();
        moviesDB.put(name, movie);
        return "Movie added successfully";
    }

    public String addDirector(Director director){
        String name=director.getName();
        directorDB.put(name, director);
        return "Director added successfully";
    }


    public String addMovieDirectorPair(String director, String movie){
        if(!directorMovieDB.containsKey(director)){
            directorMovieDB.put(director, new ArrayList<>());
        }
        directorMovieDB.get(director).add(movie);
        return "Pair made successfully";
    }

    public Movie  getMovieByName(String name){
        if(!moviesDB.containsKey(name)){
            return null;
        }
        return moviesDB.get(name);
    }

    public Director getDirectorByName(String name){
        if(!directorDB.containsKey(name)){
            return null;
        }
        return directorDB.get(name);
    }

    public List<String>  getMoviesByDirectorName(String name){
        return directorMovieDB.get(name);
    }

    public List<String> findAllMovies(){
        List<String> movies=new ArrayList<>();
        for(String name:moviesDB.keySet()){
            movies.add(name);
        }
        return movies;
    }

    public String deleteDirectorByName(String name){
        if(!directorDB.containsKey(name) && !directorMovieDB.containsKey(name)){
            return "Director is not there";
        }
        List<String> movies=directorMovieDB.get(name);
        for(String movie:movies){
            moviesDB.remove(movie);
        }
        directorMovieDB.remove(name);
        directorDB.remove(name);
        return "Data deleted successfully";
    }

    public String deleteAllDirectors(){
        for(List<String> movies:directorMovieDB.values()){
            for(String movie:movies){
                moviesDB.remove(movie);
            }
        }
        directorMovieDB.clear();
        directorDB.clear();
        return "All director data deleted successfully";
    }
}
