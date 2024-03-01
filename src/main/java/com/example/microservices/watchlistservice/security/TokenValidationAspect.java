package com.example.microservices.watchlistservice.security;

import com.example.microservices.watchlistservice.controller.BaseController;
import com.example.microservices.watchlistservice.controller.LoginController;
import com.example.microservices.watchlistservice.controller.LoginControllerInterface;
import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.service.WatchListService;
import io.netty.handler.codec.http.HttpMethod;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.FacesWebRequest;
import org.springframework.web.jsf.FacesContextUtils;
import org.springframework.web.servlet.ModelAndView;

import java.net.http.HttpConnectTimeoutException;
import java.util.Objects;

@Aspect
@Component
public class TokenValidationAspect {
    @Autowired
    private WatchListService watchListService;

    @Autowired
    HttpServletResponse httpServletResponse;


    private User getcurrentUser(){
        return watchListService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Before("execution(* com.example.microservices.watchlistservice.controller.WatchListController.*(..))")
    public void tokenSys() throws Exception {
        User current = getcurrentUser();
        current.setToken(watchListService.validateAndUpdateToken(current.getToken()));
        watchListService.saveUser(current);
        if (Objects.equals(getcurrentUser().getToken(), "nonValidToken")){
            httpServletResponse.sendRedirect("/logout");
        }
    }

    @Before("execution(* com.example.microservices.watchlistservice.controller.AdminController.*(..))")
    public void tokenSysadmin() throws Exception {
        User current = getcurrentUser();
        current.setToken(watchListService.validateAndUpdateToken(current.getToken()));
        watchListService.saveUser(current);
        if (Objects.equals(getcurrentUser().getToken(), "nonValidToken")){
            httpServletResponse.sendRedirect("/logout");
        }
    }
}
