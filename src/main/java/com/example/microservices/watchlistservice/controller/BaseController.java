package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.security.WebSecurityConfig;
import com.example.microservices.watchlistservice.service.WatchListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public abstract class BaseController {

    private WatchListService watchListService;

    protected User getCurrentUser(){
        return watchListService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public BaseController(WatchListService watchListService){
        this.watchListService = watchListService;
    }

}
