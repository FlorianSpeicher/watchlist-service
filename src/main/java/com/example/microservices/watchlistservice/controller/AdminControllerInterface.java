package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.entity.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("unused")
public interface AdminControllerInterface {

    void tokenSys();
    String showAdminPage();
    String adminShowUserList(Model model);
    String adminShowMovieList(Model model);
    String adminShowUpdateUser(@RequestParam("userId") int id, Model model);
    String adminShowUpdateMovie(@RequestParam("movieId") int id, Model model);
    String adminUpdateUser(@RequestParam("user") User user);
    String adminUpdateMovie(@RequestParam("movie") Movie movie);
    String adminDeleteMovie(@RequestParam("movieId") int id);
    String adminDeleteUser(@RequestParam("userId") int id);
}
