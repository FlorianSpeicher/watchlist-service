package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.entity.Role;
import com.example.microservices.watchlistservice.entity.Roles;
import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.service.EntityService;
import com.example.microservices.watchlistservice.service.WatchListService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/")
public class LoginController extends BaseController implements LoginControllerInterface{

    private final WatchListService watchListService;
    private final EntityService entityService;
    private WatchListController watchListController;

    public LoginController(WatchListService watchListService, EntityService entityService){
        this.watchListService = watchListService;
        this.entityService = entityService;
    }

    @Override
    @GetMapping("/addUser")
    public ModelAndView addUser(WebRequest webRequest){
        ModelAndView modelAndView = new ModelAndView("login/registration");
        modelAndView.addObject("user", new User("", "", "", "", 0, "", "", null, new Role()));
        System.out.println("addUserMethod");
        System.out.println(modelAndView.getModel());
        return modelAndView;
    }

    @RequestMapping(value="/addNewUser/redirect", method = RequestMethod.POST)
    public ModelAndView addNewUserRedirect(@ModelAttribute User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        System.out.println(bindingResult.hasErrors()+ "\n\n");
        System.out.println(user);
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setAge(user.getAge());
        newUser.setEmail(user.getEmail());
        newUser.setActive(1);
        newUser.setToken("");
        newUser.setWatchLists(new ArrayList<>());

        Role role = watchListService.findRoleById(1);

        newUser.setRoles(role);
        System.out.println(newUser.toString() + "ich bins");
        watchListService.saveUser(newUser);
        return modelAndView;
    }

    @Override
    @GetMapping("/login")
    public ModelAndView login(){
        //getCurrentUser().setToken(watchListService.generateToken());
        ModelAndView modelAndView = new ModelAndView("login/login");
        System.out.println("addUserMethod");
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/authenticateUser", method = RequestMethod.POST)
    public ModelAndView authenticateUser(@ModelAttribute User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/watchlist/home");
        System.out.println("authenticateUserMethod");
        if (watchListService.findUserByUserName(user.getUserName()) != null){
            System.out.println(watchListService.findUserByUserName(user.getUserName()));
        }

        return modelAndView;
    }



}


