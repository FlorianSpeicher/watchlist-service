package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.entity.Role;
import com.example.microservices.watchlistservice.entity.Roles;
import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.entity.WatchList;
import com.example.microservices.watchlistservice.service.EntityService;
import com.example.microservices.watchlistservice.service.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
//@RequestMapping("/")
public class LoginController extends BaseController implements LoginControllerInterface{

    private final WatchListService watchListService;
    private final EntityService entityService;
    private WatchListController watchListController;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginController(WatchListService watchListService, EntityService entityService){
        super(watchListService);
        this.watchListService = watchListService;
        this.entityService = entityService;
    }

    @Override
    @GetMapping("/addUser")
    public ModelAndView addUser(WebRequest webRequest){
        ModelAndView modelAndView = new ModelAndView("login/registration");
        modelAndView.addObject("user", new User("", "", "", "", 0, "", "", new Role()));
        return modelAndView;
    }

    @RequestMapping(value="/addNewUser/redirect", method = RequestMethod.POST)
    public ModelAndView addNewUserRedirect(@ModelAttribute User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUserName(user.getUserName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setAge(user.getAge());
        newUser.setEmail(user.getEmail());
        newUser.setActive(1);
        newUser.setToken("0");
        watchListService.saveUser(newUser);
        return modelAndView;
    }


    @Override
    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @Override
    @GetMapping("/tokenLoginSet")
    public ModelAndView tokenLoginSet(){
        ModelAndView modelAndView = new ModelAndView("redirect:/watchlist/showHome");
        getCurrentUser().setToken(watchListService.generateToken());
        return modelAndView;
    }

}


