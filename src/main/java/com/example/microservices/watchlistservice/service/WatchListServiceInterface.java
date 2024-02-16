package com.example.microservices.watchlistservice.service;

import com.example.microservices.watchlistservice.dto.Actor;
import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.dto.Regisseur;
import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.entity.WatchList;

import java.util.List;
import java.util.Optional;

public interface WatchListServiceInterface {
    

    String generateToken();
    String validateAndUpdateToken(String token);
    void invalidateToken(String user);

    List<Actor> findAllActors();

    List<Regisseur> findAllRegisseurs();

    void saveUser(User currentUser);

    Movie findMovieById(int id);

    WatchList findWatchListById(int watchListId);

    void saveWatchList(WatchList watchList);

    void saveMovie(Movie movie);

    Regisseur findRegisseurById(int id);

    Actor findActorById(int id);

    void deleteUser(User currentUser);

    List<User> findAllUsers();

    void addCommentToMovie(int id, String comment);

    User findUserById(int id);

    List<Movie> findAllMovies();

    List<WatchList> findAllWatchListsByUser(User currentUser);

    void deleteMovieById(int id);

    void deleteUserById(int id);

    Object getMoviesByActorId(int id);

    Object getMoviesByRegisseurId(int id);

    void deleteMovieFromWatchList(int watchListId, int movieId);

    void deleteWatchList(int id);
}
