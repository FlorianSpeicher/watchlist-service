package com.example.microservices.watchlistservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie-watchlist")
public class MovieWatchListConnection {

    @Id
    @GeneratedValue
    @JoinColumn(name = "id", columnDefinition = "watchlist_id")
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


    public MovieWatchListConnection(int watchListId, int movieId) {
        this.watchListId = watchListId;
        this.movieId = movieId;
    }

    public MovieWatchListConnection() {
    }
}
