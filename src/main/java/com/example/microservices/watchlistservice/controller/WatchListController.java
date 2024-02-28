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
        System.out.println(user.getUserName() + " " + user.getId() + "hier richtig in showHome");
        System.out.println("danach");
        //getCurrentUser().setToken(watchListService.generateToken());
        ModelAndView modelAndView = new ModelAndView("watchlist/home");
        System.out.println("Zwischenschritt");
        List<WatchList> userWatchLists = watchListService.findAllWatchListsOfUser(user.getId());

        List<WatchList> allWatchLists = new ArrayList<>();
        WatchList watchList1 = new WatchList();
        WatchList watchList2 = new WatchList();
        allWatchLists.add(watchList1);
        allWatchLists.add(watchList2);

        System.out.println("userWatchLists");
        System.out.println(userWatchLists);
        System.out.println("\n\n");
        modelAndView.addObject("watchLists", userWatchLists);
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
        System.out.println("/showListOfAllMovies: " + allMovies + "\n\n");
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
        //TODO schauen, ob das so geht um die id mitzugeben
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
    public String deleteMovieFromWatchList(@RequestParam("watchListId") int watchListId, @RequestParam("movieId") int movieId){
        //TODO Problem siehe 2 Methoden obendrüber
        watchListService.deleteMovieFromWatchList(watchListId, movieId);
        return "redirect:/watchlist/showSingleWatchList" + watchListId;
    }

    @GetMapping("/deleteWatchList")
    public ModelAndView deleteWatchList(@RequestParam("watchListId") int id){
        watchListService.deleteWatchList(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/watchlist/showHome");
        return modelAndView;
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
        model.addAttribute("watchLists", watchListService.findAllWatchListsOfUser(id));
        return "movie/adding-movie";
    }

    @GetMapping("/addingMovieToWatchList")
    public String addingMovieToWatchList(@RequestParam("movieId") int movieId, @RequestParam("watchListId") int watchListId){
        WatchList watchList = watchListService.findWatchListById(watchListId);
       // watchList.addMovie(watchListService.findMovieById(movieId));
        MovieWatchListConnection connection = new MovieWatchListConnection(watchListId, movieId);
        watchListService.addMovieToWatchList(connection);
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
