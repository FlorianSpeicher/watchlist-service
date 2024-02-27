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
        System.out.println("getCurrentUser");
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
        System.out.println(watchListService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).getUserName());
        return watchListService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());



        //System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
        //HttpServletRequest httpServletRequest = null;
        //Principal principal = httpServletRequest.getUserPrincipal();
        //System.out.println(principal.getName());
        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        System.out.println("Hallo");
        System.out.println(SecurityContextHolder.getContext().getAuthentication());

         */
        //return new User();
    }

    public BaseController(WatchListService watchListService){
        this.watchListService = watchListService;
    }

}
