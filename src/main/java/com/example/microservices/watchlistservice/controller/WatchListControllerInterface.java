package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.entity.WatchList;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("unused")
public interface WatchListControllerInterface {
    void tokenSys();
    String showHome(Model model);
    ModelAndView showListOfAllMovies();
    String showActorList(Model model);
    String showRegisseurList(Model model);
    String showWatchListAddPage();
    String addingWatchListToUser(@RequestParam("watchList") WatchList watchList);
    String showSingleWatchList(@RequestParam("watchListId") int id, Model model);
    String deleteMovieFromWatchList(@RequestParam("watchListId") int watchListId, @RequestParam("movieId") int movieId);
    String deleteWatchList(@RequestParam("watchListId") int id);
    String showMovieListToAddWatchList(@RequestParam("watchListId") int id, Model model);
    String showSingleMovie(@RequestParam("movieId") int id, Model model);
    String showMovieAddPage(@RequestParam("movieId") int id, Model model);
    String addingMovieToWatchList(@RequestParam("movieId") int movieId, @RequestParam("watchListId") int watchListId);
    String showCommentAddPage(@RequestParam("movieId") int id, Model model);
    String addingCommentToMovie(@RequestParam("movieId") int id,@RequestParam("comment") String comment);
    String showSingleRegisseur(@RequestParam("regisseurId") int id, Model model);
    String showSingleActor(@RequestParam("actorId") int id, Model model);
    String deleteAccount();
}
