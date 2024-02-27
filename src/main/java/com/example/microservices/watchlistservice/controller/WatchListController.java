package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.dto.Actor;
import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.dto.Regisseur;
import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.entity.WatchList;
import com.example.microservices.watchlistservice.security.CustomUserDetailsService;
import com.example.microservices.watchlistservice.service.WatchListService;
import com.example.microservices.watchlistservice.utils.StringConverter;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/watchlist")
public class WatchListController extends BaseController implements WatchListControllerInterface{

    private final WatchListService watchListService;
    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    public WatchListController(WatchListService watchListService){
        super(watchListService);
        this.watchListService = watchListService;
    }

    @Before("execution(* com.example.microservices.watchlistservice.controller.WatchListController.*")
    public void tokenSys(){
        getCurrentUser().setToken(watchListService.validateAndUpdateToken(getCurrentUser().getToken()));
        if (Objects.equals(getCurrentUser().getToken(), "NonValidToken")){
            LoginControllerInterface.accessDeniedPage();
        }
    }

    @GetMapping("/showHome")
    public ModelAndView showHome(Model model){
        System.out.println("davor");
        User user = getCurrentUser();
        System.out.println(user.getUserName() + "hier richtig in showHome");
        System.out.println("danach");
        //getCurrentUser().setToken(watchListService.generateToken());
        ModelAndView modelAndView = new ModelAndView("watchlist/home");
        System.out.println("Zwischenschritt");
        List<WatchList> userWatchLists = user.getWatchLists();

        List<WatchList> allWatchLists = new ArrayList<>();
        allWatchLists.addAll(userWatchLists);

        System.out.println("userWatchLists");
        modelAndView.addObject("watchlists", allWatchLists);
        System.out.println("ende");
        return modelAndView;
    }

    /*
    @GetMapping("/showListOfAllMovies")
    public String showListOfAllMovies(Model model){
        List<Movie> allMovies = watchListService.findAllMovies();
        model.addAttribute("movies", allMovies);
        return "movie-list-indep";
    }

     */

    @GetMapping("/showListOfAllMovies")
    public ModelAndView showListOfAllMovies(){
        List<Movie> allMovies = watchListService.findAllMovies();
        ModelAndView modelAndView = new ModelAndView("movie/movie-list-indep");
        modelAndView.addObject("movies", allMovies);
        return modelAndView;
    }

    @GetMapping("/showActorList")
    public ModelAndView showActorList(){
        List<Actor> allActors = watchListService.findAllActors();
        ModelAndView modelAndView = new ModelAndView("actor/actor-list");
        modelAndView.addObject("actors", allActors);
        return modelAndView;
    }

    @GetMapping("/showRegisseurList")
    public ModelAndView showRegisseurList(){
        List<Regisseur> allRegisseur = watchListService.findAllRegisseurs();
        ModelAndView modelAndView = new ModelAndView("regisseur/regisseur-list");
        modelAndView.addObject("regisseurs", allRegisseur);
        return modelAndView;
    }

    @GetMapping("/showWatchListAddPage")
    public ModelAndView showWatchListAddPage(){
        ModelAndView modelAndView = new ModelAndView("watchlist/adding-watchlist");
        return modelAndView;
    }

    @GetMapping("/addingWatchListToUser")
    public String addingWatchListToUser(@RequestParam("watchList") WatchList watchList){
        getCurrentUser().addWatchLists(watchList);
        watchListService.saveUser(getCurrentUser());
        //TODO schauen, ob das so geht um die id mitzugeben
        return "redirect:/watchlist/showSingleWatchList" + watchList.getId();
    }

    @GetMapping("/showSingleWatchList")
    public String showSingleWatchList(@RequestParam("watchListId") int id, Model model){
        WatchList watchList = watchListService.findWatchListById(id);
        model.addAttribute("watchList", watchList);
        return "watchlist/single-watchlist";
    }

    @GetMapping("/deleteMovieFromWatchList")
    public String deleteMovieFromWatchList(@RequestParam("watchListId") int watchListId, @RequestParam("movieId") int movieId){
        //TODO Problem siehe 2 Methoden obendrüber
        watchListService.deleteMovieFromWatchList(watchListId, movieId);
        return "redirect:/watchlist/showSingleWatchList" + watchListId;
    }

    @GetMapping("/deleteWatchList")
    public String deleteWatchList(@RequestParam("watchListId") int id){
        watchListService.deleteWatchList(id);
        return "redirect:/watchlist/showHome";
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
    public String addingMovieToWatchList(@RequestParam("movieId") int movieId, @RequestParam("watchListId") int watchListId){
        WatchList watchList = watchListService.findWatchListById(watchListId);
       // watchList.addMovie(watchListService.findMovieById(movieId));
        watchList.addMovie(movieId);
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
    public String addingCommentToMovie(@RequestParam("movieId") int id,@RequestParam("comment") String comment){
        Movie movie = watchListService.findMovieById(id);
        watchListService.addCommentToMovie(id, StringConverter.stringToCommentString(comment));
        watchListService.saveMovie(movie);
        return "redirect:/watchlist/showSingleMovie";
    }

    @GetMapping("/showSingleRegisseur")
    public String showSingleRegisseur(@RequestParam("regisseurId") int id, Model model){
        Regisseur regisseur = watchListService.findRegisseurById(id);
        model.addAttribute("regisseur", regisseur);
        model.addAttribute("movies", watchListService.getMoviesByRegisseurId(id));
        return "regisseur/regisseur";
    }

    @GetMapping("/showSingleActor")
    public String showSingleActor(@RequestParam("actorId") int id, Model model){
        Actor actor = watchListService.findActorById(id);
        model.addAttribute("actor", actor);
        model.addAttribute("movies", watchListService.getMoviesByActorId(id));
        return "actor/actor";
    }

    @GetMapping("/deleteAccount")
    public String deleteAccount(){
        watchListService.deleteUser(getCurrentUser());
        return "redirect:/";
    }

}
