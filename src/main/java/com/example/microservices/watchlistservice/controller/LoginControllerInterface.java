package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.entity.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("unused")
public interface LoginControllerInterface {

    static String accessDeniedPage() {
        return "login/access-denied";
    }

    ModelAndView login();
    ModelAndView addUser(WebRequest webRequest);

    ModelAndView tokenLoginSet();
}
