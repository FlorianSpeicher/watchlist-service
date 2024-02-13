package com.example.microservices.watchlistservice.service;

import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.entity.WatchList;

import java.util.List;

public interface WatchListServiceInterface {
    List<WatchList> findAllWatchLists();
    List<String> findAllMovies();

    WatchList findWatchListByID(int id);
    String findMovieByID(int id);

    List<WatchList> findWatchListByName(String name);
    String findMovieByTitle(String title);
    String findMovieByActor(String actor);
    String findMovieByRegisseur(String regisseur);

    WatchList saveWatchList(WatchList watchList);
    String saveComment(int id, String comment);
    String saveMovie(String movie);

    String updateMovie(int id, String movie);

    void deleteMovieByID(int id);
    void deleteCommentByID(int id);
    void deleteWatchlistByID(int id);

    //User

    String addUser(User user);
    void deleteUserByID(int id);

    String generateToken();
    String validateAndUpdateToken(String token);
    void invalidateToken(String user);
}
