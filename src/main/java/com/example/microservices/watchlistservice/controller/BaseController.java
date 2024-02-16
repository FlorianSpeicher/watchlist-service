package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.service.WatchListService;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public abstract class BaseController {

    private WatchListService watchListService;
    protected User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
