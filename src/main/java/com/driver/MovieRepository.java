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
        return "Success";
    }

    public String addDirector(Director director){
        String name=director.getName();
        directorDB.put(name, director);
        return "Success";
    }


    public String addMovieDirectorPair(String movie, String director){
        if(moviesDB.containsKey(movie) && directorDB.containsKey(director)){
            if(!directorMovieDB.containsKey(director)){
                directorMovieDB.put(director, new ArrayList<>());
            }
            List<String> movies=directorMovieDB.get(director);
            if(movies.contains(movie)){
                return "Pair is already there";
            }
            movies.add(movie);
            directorMovieDB.put(director, movies);
            return "Success";
        }
        return "Some error";
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
        if(directorMovieDB.containsKey(name)){
            return directorMovieDB.get(name);
        }
        return null;
    }

    public List<String> findAllMovies(){
        List<String> movies=new ArrayList<>();
        for(String name:moviesDB.keySet()){
            movies.add(name);
        }
        return movies;
    }

    public String deleteDirectorByName(String name){
        if(!directorDB.containsKey(name)){
            return "Director is not there";
        }
        if(directorMovieDB.containsKey(name)){
            List<String> movies=new ArrayList<>();
            movies=directorMovieDB.get(name);
            for(String movie:movies){
                moviesDB.remove(movie);
            }
            directorMovieDB.remove(name);
        }
        directorDB.remove(name);
        return "Success";
    }

    public String deleteAllDirectors(){
        if(directorMovieDB.size()>0){
            for(List<String> movies:directorMovieDB.values()){
                for(String movie:movies){
                    moviesDB.remove(movie);
                }
            }
        }
        directorMovieDB.clear();
        directorDB.clear();
        return "Success";
    }
}
