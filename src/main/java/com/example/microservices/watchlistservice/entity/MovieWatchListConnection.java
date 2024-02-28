package com.example.microservices.watchlistservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie-watchlist")
public class MovieWatchListConnection {

    @Id
    @GeneratedValue
    @JoinColumn(name = "id")
    private int watchlistId;

    @Column(name = "movie_id")
    private int movieId;

    public int getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(int watchlistId) {
        this.watchlistId = watchlistId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }


    public MovieWatchListConnection(int watchlistId, int movieId) {
        this.watchlistId = watchlistId;
        this.movieId = movieId;
    }

    public MovieWatchListConnection() {
    }
}
