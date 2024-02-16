package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.service.WatchListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController extends BaseController implements LoginControllerInterface{

    private final WatchListService watchListService;

    public LoginController(WatchListService watchListService){
        this.watchListService = watchListService;
    }

    @Override
    @GetMapping("/addUser")
    public String addUser(@RequestParam("user") User user){
        watchListService.saveUser(user);
        return "login/registration";
    }

    @Override
    @GetMapping("/login")
    public String login(){
        getCurrentUser().setToken(watchListService.generateToken());
        return "login/login";
    }

    @Override
    @GetMapping("/authenticateUser")
    public String authenticateUser(@RequestParam("user") User user){
        //TODO
        return "";
    }

}


