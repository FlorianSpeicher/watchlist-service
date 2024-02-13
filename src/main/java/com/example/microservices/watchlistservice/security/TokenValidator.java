package com.example.microservices.watchlistservice.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class TokenValidator {
    @Before("execution(* com.example.microservices.watchlistservice.controller.WatchListController.*)")
    public void validateLogin(){

    }
}
