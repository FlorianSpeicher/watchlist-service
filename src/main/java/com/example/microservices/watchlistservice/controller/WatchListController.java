package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.dto.Actor;
import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.dto.Regisseur;
import com.example.microservices.watchlistservice.entity.Roles;
import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.entity.WatchList;
import com.example.microservices.watchlistservice.service.WatchListService;
import com.example.microservices.watchlistservice.utils.StringConverter;
import org.springframework.boot.Banner;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watchlist")
public class WatchListController extends BaseController{

    private WatchListService watchListService;

    public WatchListController(WatchListService watchListService){
        this.watchListService = watchListService;
    }

    @GetMapping("/showHome")
    public String showHome(Model model){
        List<WatchList> allUserWatchLists = watchListService.findAllWatchListsByUser(getCurrentUser());
        model.addAttribute("watchLists", allUserWatchLists);
        return "/watchlist/home";
    }

    @GetMapping("/showListOfAllMovies")
    public String showListOfAllMovies(Model model){
        List<Movie> allMovies = watchListService.findAllMovies();
        model.addAttribute("movies", allMovies);
        return "/movie/movie-list-indep";
    }

    @GetMapping("/showActorList")
    public String showActorList(Model model){
        List<Actor> allActors = watchListService.findAllActors();
        model.addAttribute("actors", allActors);
        return "/actor/actor-list";
    }

    @GetMapping("/showRegisseurList")
    public String showRegisseurList(Model model){
        List<Regisseur> allRegisseur = watchListService.findAllRegisseurs();
        model.addAttribute("regisseur", allRegisseur);
        return "/regisseur/regisseur-list";
    }

    @GetMapping("/showWatchListAddPage")
    public String showWatchListAddPage(Model model){
        return "watchlist/adding-watchlist";
    }

    @GetMapping("/addingWatchListToUser")
    public String addingWatchListToUser(@RequestParam("watchList") WatchList watchList, Model model){
        getCurrentUser().addWatchLists(watchList);
        watchListService.saveUser(getCurrentUser());
        return "redirect:/watchlist/showSingleWatchList";
    }

    @GetMapping("/showSingleWatchList")
    public String showSingleWatchList(@RequestParam("watchListId") int id, Model model){
        WatchList watchList = watchListService.findWatchListByID(id);
        model.addAttribute("watchList", watchList);
        return "watchlist/single-watchlist";
    }

    @GetMapping("/showMovieListToAddWatchList")
    public String showMovieListToAddWatchList(@RequestParam("watchListId") int id, Model model){
        List<Movie> allMovies = watchListService.findAllMovies();
        model.addAttribute("movies", allMovies);
        model.addAttribute("watchListId", id);
        return "movie/movie-list-attached";
    }

    @GetMapping("/showSingleMovie")
    public String showSingleMovie(@RequestParam("movieId") int id, Model model){
        Movie movie = watchListService.findMovieById(id);
        model.addAttribute("movie", movie);
        return "movie/movie";
    }

    @GetMapping("/showMovieAddPage")
    public String showMovieAddPage(@RequestParam("movieId") int id, Model model){
        model.addAttribute("movieId", id);
        model.addAttribute("watchLists", getCurrentUser().getWatchLists());
        return "movie/adding-movie";
    }

    @GetMapping("/addingMovieToWatchList")
    public String addingMovieToWatchList(@RequestParam("movieId") int movieId, @RequestParam("watchListId") int watchListId, Model model){
        WatchList watchList = watchListService.findWatchListById(watchListId);
        watchList.addMovie(watchListService.findMovieById(movieId));
        watchListService.saveWatchList(watchList);
        //TODO RequestParam bei Übergabe beachten?
        return "redirect:/watchlist/showSingleWatchList";
    }

    @GetMapping("/showCommentAddPage")
    public String showCommentAddPage(@RequestParam("movieId") int id, Model model){
        model.addAttribute("movieId", id);
        return "movie/comment-write";
    }

    @GetMapping("/addingCommentToMovie")
    public String addingCommentToMovie(@RequestParam("movieId") int id,@RequestParam("comment") String comment, Model model){
        Movie movie = watchListService.findMovieById(id);
        watchListService.addCommentToMovie(id, StringConverter.stringToCommentString(comment));
        watchListService.saveMovie(movie);
        return "redirect:/watchlist/showSingleMovie";
    }

    @GetMapping("/showSingleRegisseur")
    public String showSingleRegisseur(@RequestParam("regisseurId") int id, Model model){
        Regisseur regisseur = watchListService.findRegisseurById(id);
        model.addAttribute("regisseur", regisseur);
        return "regisseur/regisseur";
    }

    @GetMapping("/showSingleActor")
    public String showSingleActor(@RequestParam("actorId") int id, Model model){
        Actor actor = watchListService.findActorById(id);
        model.addAttribute("actor", actor);
        return "actor/actor";
    }

    @GetMapping("/deleteAccount")
    public String deleteAccount(Model model){
        watchListService.deleteUser(getCurrentUser());
        return "redirect:/";
    }

}
