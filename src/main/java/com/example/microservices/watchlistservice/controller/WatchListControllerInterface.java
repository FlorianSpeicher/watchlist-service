package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.entity.WatchList;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("unused")
public interface WatchListControllerInterface {
    //void tokenSys();
    ModelAndView showHome(Model model);
    ModelAndView showListOfAllMovies();
    ModelAndView showActorList();
    ModelAndView showRegisseurList();
    ModelAndView showWatchListAddPage(WebRequest webRequest);
    ModelAndView addingWatchListToUser(@ModelAttribute("watchList") WatchList watchList, BindingResult bindingResult);
    ModelAndView showSingleWatchList(@RequestParam("watchListId") int id);
    ModelAndView deleteMovieFromWatchList(@RequestParam("watchListId") int watchListId, @RequestParam("movieId") int movieId);
    ModelAndView deleteWatchList(@RequestParam("watchListId") int id);
    ModelAndView showMovieListToAddWatchList(@RequestParam("watchListId") int id);
    ModelAndView showSingleMovie(@RequestParam("movieId") int id);
    ModelAndView showMovieAddPage(@RequestParam("movieId") int id);
    ModelAndView addingMovieToWatchList(@RequestParam("movieId") int movieId, @RequestParam("watchListId") int watchListId);
    ModelAndView showCommentAddPage(@RequestParam("movieId") int id);
    ModelAndView addingCommentToMovie(@RequestParam("movieId") int id,@RequestParam("comment") String comment);
    ModelAndView showSingleRegisseur(@RequestParam("regisseurId") int id);
    ModelAndView showSingleActor(@RequestParam("actorId") int id);
    ModelAndView deleteAccount();
}
