package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.entity.Role;
import com.example.microservices.watchlistservice.entity.Roles;
import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.entity.WatchList;
import com.example.microservices.watchlistservice.service.EntityService;
import com.example.microservices.watchlistservice.service.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
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
        modelAndView.addObject("user", new User("", "", "", "", 0, "", "", null, new Role()));
        System.out.println("addUserMethod");
        System.out.println(modelAndView.getModel() + "Model aus addUser");
        return modelAndView;
    }

    @RequestMapping(value="/addNewUser/redirect", method = RequestMethod.POST)
    public ModelAndView addNewUserRedirect(@ModelAttribute User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        System.out.println(bindingResult.hasErrors()+ "Binding Results");
        System.out.println(user + "user aus redirect");
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUserName(user.getUserName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setAge(user.getAge());
        newUser.setEmail(user.getEmail());
        newUser.setActive(1);
        newUser.setToken("");
        newUser.setWatchLists(new ArrayList<>());
        System.out.println("Statement vor watchlistService");
        /*
        Role role = new Role();
        int id = 1;
        role = watchListService.findRoleById(id);
        System.out.println("Statement danach");
        newUser.setRoles(role);

         */
        System.out.println(newUser.toString() + "redirect");
        watchListService.saveUser(newUser);
        return modelAndView;
    }


    @Override
    @GetMapping("/login")
    public ModelAndView login(){
        //getCurrentUser().setToken(watchListService.generateToken());
        ModelAndView modelAndView = new ModelAndView("login/login");
        System.out.println("login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }


/*
    @Override
    @RequestMapping(value = "/authenticateUser", method = RequestMethod.POST)
    public ModelAndView authenticateUser(@ModelAttribute User user, BindingResult bindingResult){
        System.out.println("Auth vor Model erstellung");
        ModelAndView modelAndView = new ModelAndView("/login/registration");
        //ModelAndView modelAndView = new ModelAndView("/watchlist/home");
        System.out.println("authenticateUserMethod");
       // modelAndView.addObject("user", new User());
       // Principal principal = (Principal) watchListService.findUserByUserName(user.getUserName());
        if (watchListService.findUserByUserName(user.getUserName()) != null){
            System.out.println(watchListService.findUserByUserName(user.getUserName()) + "auth");
        }

        return modelAndView;
    }

 */



}


