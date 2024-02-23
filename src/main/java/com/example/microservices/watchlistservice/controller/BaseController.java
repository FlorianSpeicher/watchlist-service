package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.service.WatchListService;
import jakarta.servlet.http.HttpServletRequest;
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
        if (SecurityContextHolder.getContext().getAuthentication() == null){
            System.out.println("Ist null");
        }

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println(authentication.getPrincipal());




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
        return new User();
    }

}
