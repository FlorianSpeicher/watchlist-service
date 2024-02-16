package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.service.WatchListService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController extends BaseController{

    private WatchListService watchListService;

    public LoginController(WatchListService watchListService){
        this.watchListService = watchListService;
    }


    @GetMapping("/addUser")
    public String addUser(@RequestParam("user") User user, Model model){
        watchListService.saveUser(user);
        return "login/registration";
    }

    @GetMapping("/login")
    public String login(){
        getCurrentUser().setToken(watchListService.generateToken());
        return "login/login";
    }

    @GetMapping("/access-denied")
    public static String accessDeniedPage(){
        return "login/access-denied";
    }


    @GetMapping("/authenticateUser")
    public String authenticateUser(@RequestParam("user") User user){
        //TODO
        return "";
    }

}


