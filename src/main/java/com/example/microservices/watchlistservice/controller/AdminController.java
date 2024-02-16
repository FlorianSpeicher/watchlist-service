package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.entity.Role;
import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.service.WatchListService;
import org.aspectj.lang.annotation.Before;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static com.example.microservices.watchlistservice.entity.Roles.*;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController{

    private WatchListService watchListService;

    @Before("execution(* com.example.microservices.watchlistservice.controller.AdminController.*")
    public void tokenSys(){
        getCurrentUser().setToken(watchListService.validateAndUpdateToken(getCurrentUser().getToken()));
        if (Objects.equals(getCurrentUser().getToken(), "NonValidToken")){
            LoginController.accessDeniedPage();
        }
    }

    @GetMapping("/showAdminPage")
    public String showAdminPage(Model model){
        Role role = new Role();
        role.setRole(ADMIN);
        User user = new User();
        user.setRoles(role);
        if (getCurrentUser().getRoles().equals(user.getRoles())){
            return "/admin/admin-page";
        }
        return "redirect:/watchlist/showHome";
    }

    @GetMapping("/adminShowUserList")
    public String adminShowUserList(Model model){
        List<User> allUsers = watchListService.findAllUsers();
        model.addAttribute("users", allUsers);
        return "/admin/admin-user";
    }

    @GetMapping("/adminShowMovieList")
    public String adminShowMovieList(Model model){
        List<Movie> allMovies = watchListService.findAllMovies();
        model.addAttribute("movies", allMovies);
        return "/admin/admin-movie";
    }

    @GetMapping("/adminShowUpdateUser")
    public String adminShowUpdateUser(@RequestParam("userId") int id, Model model){
        User user = watchListService.findUserById(id);
        model.addAttribute("user", user);
        return "/admin/admin-user-update";
    }

    @GetMapping("/adminShowUpdateMovie")
    public String adminShowUpdateMovie(@RequestParam("movieId") int id, Model model){
        Movie movie = watchListService.findMovieById(id);
        model.addAttribute("movie", movie);
        return "/admin/admin-movie-update";
    }

    @GetMapping("/adminUpdateUser")
    public String adminUpdateUser(@RequestParam("user") User user, Model model){
        watchListService.saveUser(user);
        return "redirect:/admin/adminShowUserList";
    }

    @GetMapping("/adminUpdateMovie")
    public String adminUpdateMovie(@RequestParam("movie") Movie movie, Model model){
        watchListService.saveMovie(movie);
        return "redirect:/admin/adminShowMovieList";
    }

    @GetMapping("/adminDeleteMovie")
    public String adminDeleteMovie(@RequestParam("movieId") int id){
        watchListService.deleteMovieById(id);
        return "redirect:/admin/adminShowMovieList";
    }

    @GetMapping("/adminDeleteUser")
    public String adminDeleteUser(@RequestParam("userId") int id){
        watchListService.deleteUserById(id);
        return "redirect:/admin/adminShowUserList";
    }
}
