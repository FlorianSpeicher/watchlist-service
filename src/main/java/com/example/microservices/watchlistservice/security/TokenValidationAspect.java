package com.example.microservices.watchlistservice.security;

import com.example.microservices.watchlistservice.controller.BaseController;
import com.example.microservices.watchlistservice.controller.LoginControllerInterface;
import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.service.WatchListService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class TokenValidationAspect {
    @Autowired
    private WatchListService watchListService;

    private User getcurrentUser(){
        return watchListService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Before("execution(* com.example.microservices.watchlistservice.controller.WatchListController.*(..))")
    public void tokenSys(){
        System.out.println("Alter Token: " + getcurrentUser().getToken());
        getcurrentUser().setToken(watchListService.validateAndUpdateToken(getcurrentUser().getToken()));
        System.out.println("tokensys");
        System.out.println(getcurrentUser().getToken());
        if (Objects.equals(getcurrentUser().getToken(), "nonValidToken")){
            System.out.println("Token abgelaufen");
            LoginControllerInterface.accessDeniedPage();
        }
    }


}
