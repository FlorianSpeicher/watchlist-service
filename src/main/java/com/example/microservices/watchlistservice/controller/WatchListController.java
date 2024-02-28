package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.dto.Actor;
import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.dto.Regisseur;
import com.example.microservices.watchlistservice.entity.MovieWatchListConnection;
import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.entity.WatchList;
import com.example.microservices.watchlistservice.security.CustomUserDetailsService;
import com.example.microservices.watchlistservice.service.WatchListService;
import com.example.microservices.watchlistservice.utils.StringConverter;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/watchlist")
public class WatchListController extends BaseController implements WatchListControllerInterface{

    @Autowired
    private  WatchListService watchListService;
    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    public WatchListController(WatchListService watchListService) {
        super(watchListService);
    }


    @GetMapping("/showHome")
    public ModelAndView showHome(Model model){
        User user = getCurrentUser();
        ModelAndView modelAndView = new ModelAndView("watchlist/home");
        List<WatchList> userWatchLists = watchListService.findAllWatchListsOfUser(user.getId());
        List<WatchList> allWatchLists = new ArrayList<>();
        WatchList watchList1 = new WatchList();
        WatchList watchList2 = new WatchList();
        allWatchLists.add(watchList1);
        allWatchLists.add(watchList2);
        modelAndView.addObject("watchLists", userWatchLists);
        return modelAndView;
    }

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
    public ModelAndView showWatchListAddPage(WebRequest webRequest){
        ModelAndView modelAndView = new ModelAndView("watchlist/adding-watchlist");
        modelAndView.addObject("watchList", new WatchList());
        return modelAndView;
    }

    @RequestMapping(value = "/addingWatchListToUser", method = RequestMethod.POST)
    public ModelAndView addingWatchListToUser(@ModelAttribute("watchList") WatchList watchList, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/watchlist/showSingleWatchList");
        watchList.setUser(getCurrentUser().getId());
        watchListService.addWatchLists(watchList);
        watchListService.saveUser(getCurrentUser());
        modelAndView.addObject("watchListId", watchList.getId());
        return modelAndView;
    }

    @GetMapping("/showSingleWatchList")
    public ModelAndView showSingleWatchList(@RequestParam("watchListId") int id){
       ModelAndView modelAndView = new ModelAndView("watchlist/single-watchlist");
        WatchList watchList = watchListService.findWatchListById(id);
        modelAndView.addObject("watchList", watchList);
        List<Movie> movieList = watchListService.findAllMoviesOfWatchList(id);
        modelAndView.addObject("movies", movieList);
        return modelAndView;
    }

    @GetMapping("/deleteMovieFromWatchList")
    public ModelAndView deleteMovieFromWatchList(@RequestParam("watchListId") int watchListId, @RequestParam("movieId") int movieId){
        //TODO Problem siehe 2 Methoden obendrüber
        ModelAndView modelAndView = new ModelAndView("redirect:/watchlist/showSingleWatchList");
        watchListService.deleteMovieFromWatchList(watchListId, movieId);
        return modelAndView;
    }

    @GetMapping("/deleteWatchList")
    public ModelAndView deleteWatchList(@RequestParam("watchListId") int id){
        watchListService.deleteWatchList(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/watchlist/showHome");
        return modelAndView;
    }

    @GetMapping("/showMovieListToAddWatchList")
    public ModelAndView showMovieListToAddWatchList(@RequestParam("watchListId") int id){
        ModelAndView modelAndView = new ModelAndView("movie/movie-list-attached");
        List<Movie> allMovies = watchListService.findAllMovies();
        modelAndView.addObject("movies", allMovies);
        modelAndView.addObject("watchListId", id);
        return modelAndView;
    }

    @GetMapping("/showSingleMovie")
    public ModelAndView showSingleMovie(@RequestParam("movieId") int id){
        ModelAndView modelAndView = new ModelAndView("movie/movie");
        Movie movie = watchListService.findMovieById(id);
        modelAndView.addObject("movie", movie);
        return modelAndView;
    }

    @GetMapping("/showMovieAddPage")
    public ModelAndView showMovieAddPage(@RequestParam("movieId") int id){
        ModelAndView modelAndView = new ModelAndView("Movie/adding-movie");
        modelAndView.addObject("movieId", id);
        modelAndView.addObject("watchLists", watchListService.findAllWatchListsOfUser(id));
        return modelAndView;
    }

    @GetMapping("/addingMovieToWatchList")
    public ModelAndView addingMovieToWatchList(@RequestParam("movieId") int movieId, @RequestParam("watchListId") int watchListId){
        ModelAndView modelAndView = new ModelAndView("redirect:/watchlist/showSingleWatchList");
        WatchList watchList = watchListService.findWatchListById(watchListId);
       // watchList.addMovie(watchListService.findMovieById(movieId));
        MovieWatchListConnection connection = new MovieWatchListConnection(watchListId, movieId);
        watchListService.addMovieToWatchList(connection);
        watchListService.saveWatchList(watchList);
        //TODO RequestParam bei Übergabe beachten?
        return modelAndView;
    }

    @GetMapping("/showCommentAddPage")
    public ModelAndView showCommentAddPage(@RequestParam("movieId") int id){
        ModelAndView modelAndView = new ModelAndView("movie/comment-write");
        modelAndView.addObject("movieId", id);
        return modelAndView;
    }

    @GetMapping("/addingCommentToMovie")
    public ModelAndView addingCommentToMovie(@RequestParam("movieId") int id,@RequestParam("comment") String comment){
        ModelAndView modelAndView = new ModelAndView("redirect:/watchlist/showSingleMovie");
        Movie movie = watchListService.findMovieById(id);
        watchListService.addCommentToMovie(id, StringConverter.stringToCommentString(comment));
        watchListService.saveMovie(movie);
        return modelAndView;
    }

    @GetMapping("/showSingleRegisseur")
    public ModelAndView showSingleRegisseur(@RequestParam("regisseurId") int id){
        ModelAndView modelAndView = new ModelAndView("regisseur/regisseur");
        Regisseur regisseur = watchListService.findRegisseurById(id);
        modelAndView.addObject("regisseur", regisseur);
        modelAndView.addObject("movies", watchListService.getMoviesByRegisseurId(id));
        return modelAndView;
    }

    @GetMapping("/showSingleActor")
    public ModelAndView showSingleActor(@RequestParam("actorId") int id){
        ModelAndView modelAndView = new ModelAndView("actor/actor");
        Actor actor = watchListService.findActorById(id);
        modelAndView.addObject("actor", actor);
        modelAndView.addObject("movies", watchListService.getMoviesByActorId(id));
        return modelAndView;
    }

    @GetMapping("/deleteAccount")
    public ModelAndView deleteAccount(){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        watchListService.deleteUser(getCurrentUser());
        return modelAndView;
    }

}
