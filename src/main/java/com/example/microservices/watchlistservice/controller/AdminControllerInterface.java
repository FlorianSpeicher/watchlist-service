package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.entity.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("unused")
public interface AdminControllerInterface {

    ModelAndView showAdminPage();
    ModelAndView adminShowUserList();
    ModelAndView adminShowMovieList();
    ModelAndView adminShowUpdateUser(@RequestParam("userId") int id);
    ModelAndView adminShowUpdateMovie(@RequestParam("movieId") int id);
    ModelAndView adminUpdateUser(@ModelAttribute("user") User user, BindingResult bindingResult);
    ModelAndView adminUpdateMovie(@ModelAttribute("movie") Movie movie, BindingResult bindingResult);
    ModelAndView adminDeleteMovie(@RequestParam("movieId") int id);
    ModelAndView adminDeleteUser(@RequestParam("userId") int id);
}
