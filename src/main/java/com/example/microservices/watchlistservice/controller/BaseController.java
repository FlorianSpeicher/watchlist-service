package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.service.WatchListService;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public abstract class BaseController {

    private WatchListService watchListService;
    protected User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Before("execution(* com.example.microservices.watchlistservice.controller.*")
    public void tokenSys(){
        String token = getCurrentUser().getToken();
        getCurrentUser().setToken(watchListService.validateAndUpdateToken(getCurrentUser().getToken());
        if (Objects.equals(getCurrentUser().getToken(), "NonValidToken")){
//TODO
        }
    }

    public void generateToken(){
        getCurrentUser().setToken(watchListService.generateToken());
    }

    public void invalidateToken(){
        watchListService.invalidateToken(getCurrentUser().getToken());
    }
}
