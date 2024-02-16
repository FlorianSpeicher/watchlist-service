package com.example.microservices.watchlistservice.entity;

import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.utils.watchlist.ValidWatchlistName;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "watchlist")
public class WatchList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @ValidWatchlistName
    private String name;

    @Column(name = "movies")
    private List<Movie> movies;

    private String userName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie){
        this.movies.add(movie);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
