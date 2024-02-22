package com.example.microservices.watchlistservice.entity;

import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.utils.watchlist.ValidWatchlistName;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "watchlist")
@SuppressWarnings("unused")
public class WatchList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @ValidWatchlistName
    private String name;

    @Column(name = "movies")
    private List<Integer> movies;

    @ManyToOne
    @JoinTable(name = "user")
    private User user;


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

    public List<Integer> getMovies() {
        return movies;
    }

    public void setMovies(List<Integer> movies) {
        this.movies = movies;
    }

    public void addMovie(int movie){
        this.movies.add(movie);
    }

    public User getUserName() {
        return user;
    }

    public void setUserName(User user) {
        this.user = user;
    }
}
