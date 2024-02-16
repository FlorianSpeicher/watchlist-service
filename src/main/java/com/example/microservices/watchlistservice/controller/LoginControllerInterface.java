package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("unused")
public interface LoginControllerInterface {

    String authenticateUser(@RequestParam("user") User user);

    static String accessDeniedPage() {
        //TODO RückgabeWert
        return "login/access-denied";
    }

    String login();
    String addUser(@RequestParam("user") User user);
}
