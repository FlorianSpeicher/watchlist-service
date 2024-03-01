package com.example.microservices.watchlistservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_watchlist")
public class MovieWatchListConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "watchlist_id")
    private int watchListId;

    @Column(name = "movie_id")
    private int movieId;

    public int getWatchListId() {
        return watchListId;
    }

    public void setWatchListId(int watchListId) {
        this.watchListId = watchListId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MovieWatchListConnection(int watchListId, int movieId) {
        this.watchListId = watchListId;
        this.movieId = movieId;
    }

    public MovieWatchListConnection() {
    }
}
