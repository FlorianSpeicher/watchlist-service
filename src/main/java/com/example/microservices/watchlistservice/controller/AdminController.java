package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.entity.Role;
import com.example.microservices.watchlistservice.entity.Roles;
import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.service.EntityService;
import com.example.microservices.watchlistservice.service.WatchListService;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

import static com.example.microservices.watchlistservice.entity.Roles.*;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController implements AdminControllerInterface{

    @Autowired
    private WatchListService watchListService;

    @Autowired
    private EntityService entityService;

    public AdminController(WatchListService watchListService) {
        super(watchListService);
    }


    @Override
    @GetMapping("/showAdminPage")
    public ModelAndView showAdminPage(){
        ModelAndView modelAndView = new ModelAndView();
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(ROLE_ADMIN.name());

        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(auth)){
            modelAndView.setViewName("admin/admin-page");
        } else {
            modelAndView.setViewName("redirect:/watchlist/showHome");
        }

        return modelAndView;
    }

    @Override
    @GetMapping("/adminShowUserList")
    public ModelAndView adminShowUserList(){
        ModelAndView modelAndView = new ModelAndView("admin/admin-user");
        List<User> allUsers = watchListService.findAllUsers();
        modelAndView.addObject("users", allUsers);
        return modelAndView;
    }

    @Override
    @GetMapping("/adminShowMovieList")
    public ModelAndView adminShowMovieList(){
        ModelAndView modelAndView = new ModelAndView("admin/admin-movie");
        List<Movie> allMovies = watchListService.findAllMovies();
        modelAndView.addObject("movies", allMovies);
        return modelAndView;
    }

    @Override
    @GetMapping("/adminShowUpdateUser")
    public ModelAndView adminShowUpdateUser(@RequestParam("userId") int id){
        ModelAndView modelAndView = new ModelAndView("admin/admin-user-update");
        User user = watchListService.findUserById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @Override
    @GetMapping("/adminShowUpdateMovie")
    public ModelAndView adminShowUpdateMovie(@RequestParam("movieId") int id){
        ModelAndView modelAndView = new ModelAndView("admin/admin-movie-update");
        Movie movie = watchListService.findMovieById(id);
        modelAndView.addObject("movie", movie);
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/adminUpdateUser", method = RequestMethod.POST)
    public ModelAndView adminUpdateUser(@ModelAttribute("user") User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/adminShowUserList");
        entityService.saveUser(user);
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/adminUpdateMovie", method = RequestMethod.POST)
    public ModelAndView adminUpdateMovie(@ModelAttribute("movie") Movie movie, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/adminShowMovieList");
        watchListService.saveMovie(movie);
        return modelAndView;
    }

    @Override
    @GetMapping("/adminDeleteMovie")
    public ModelAndView adminDeleteMovie(@RequestParam("movieId") int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/adminShowMovieList");
        watchListService.deleteMovieById(id);
        return modelAndView;
    }

    @Override
    @GetMapping("/adminDeleteUser")
    public ModelAndView adminDeleteUser(@RequestParam("userId") int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/adminShowUserList");
        watchListService.deleteUserById(id);
        return modelAndView;
    }
}
