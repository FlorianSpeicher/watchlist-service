package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.entity.WatchList;
import com.example.microservices.watchlistservice.service.WatchListService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watchlist")
public class WatchListController {

    private WatchListService watchListService;

    public WatchListController(WatchListService watchListService){
        this.watchListService = watchListService;
    }

    @GetMapping("/listAll")
    public String listAllWatchLists(Model model){
        //TODO USer erkennung
     List<WatchList> allWatchlists = watchListService.findAllWatchListsByUser();
     model.addAttribute("watchlists", allWatchlists);
     return "home";
    }

    @GetMapping("/listAllMovies")
    public String listAllMovies(Model model){
        List<String> allMovieNames = watchListService.findAllMovieNames();
        //TODO Methode um nur die namen der Filme rauszufinden
        model.addAttribute("movieNames", allMoviesNames);
        return "movie-list-indep";
    }

    @GetMapping("/editWatchList")
    public String editWatchlist(@RequestParam("watchListId") int id, Model model){
        WatchList watchList = watchListService.findWatchListByID(id);
        model.addAttribute("watchlist", watchList);
        return "single-watchlist";
    }

    @GetMapping("/deleteWatchList")
    public String deleteWatchList(@RequestParam("watchListId") int id, Model model){
        watchListService.deleteWatchlistByID(id);
        return "redirect:/watchlist/listAll";
    }

    @GetMapping("/deleteMovieById")
    public String deleteMovieById(@RequestParam("movieId") int id, Model model){
        //TODO implement Methode, damit nicht der Film gelöscht wird, sondern nur die Referenz in der Watchlist
        watchListService.deleteMovieFromWatchListByID(id);
        return "redirect:/watchlist/addMovieToWatchListAttached";
    }

    @GetMapping("/addWatchList")
    public String addWatchList(Model model){
        WatchList watchList = new WatchList();
        model.addAttribute("watchlist", watchList);
        return "adding-watchlist";
    }

    @GetMapping("/addMovieToWatchListAttached")
    public String addMovieToWatchListPageDirect(){
        return "movie-list-attached";
    }

    @GetMapping("/addMovieToWatchList")
    public String addMovieToWatchList(@RequestParam("watchListId") int watchListId, @RequestParam("movieId") int movieId, Model model){
        WatchList watchList = watchListService.findWatchListByID(watchListId);
        //TODO implement addMovie to watchlist.class
        //TODO warum model hier verwenden
        watchList.addMovie(movieId);
        watchListService.saveWatchList(watchList);
        return "redirect:/watchlist/editWatchList";
    }
    @GetMapping("/addMovieToWatchListIndep")
    public String addMovieToWatchListIndep(@RequestParam("movieId") int id, Model model){
        String movie = watchListService.findMovieByID(id);
        model.addAttribute("movie", movie);
        return "adding-movie";
    }

    @GetMapping("/addUser")
    public String addUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage(){
        return "access-denied";
    }

    @GetMapping("/showAdminPage")
    public String showAdminPage(){
        return "admin-page";
    }

    @GetMapping("/listSingleMovieById")
    public String listSingleMovieById(@RequestParam("movieId") int id, Model model){
        String movie = watchListService.findMovieByID(id);
        model.addAttribute("movie", movie);
        return "movie";
    }

    @GetMapping("/addIndepMovieToWatchList")
    public String addIndepMovieToWatchList(@RequestParam("watchlistId") int watchlistId, @RequestParam("movieId") int movieId, Model model){
        WatchList watchList = watchListService.findWatchListByID(watchlistId);
        String movie = watchListService.findMovieByID(movieId);
        watchList.addMovie(movie);
        watchListService.saveWatchList(watchList);
        //TODO schauen ob Parameter nötig sind
        return "redirect:/watchlist/editWatchList";
    }

    @GetMapping("/commentWrite")
    public String commentWrite(@RequestParam("movieId") int id, Model model){
        //TODO implement method
        return "comment-write";
    }

    @GetMapping("/adminUserList")
    public String adminUserList(){
        return "admin-user";
    }

    @GetMapping("/adminMovieList")
    public String adminMovieList(){
        return "admin-movie";
    }

    @GetMapping("/adminUpdateMovie")
    public String adminUpdateMovie(@RequestParam("movieId") int id, Model model){
        return "admin-movie-update";
    }

    @GetMapping("/adminDeleteMovie")
    public String adminDeleteMovie(@RequestParam("movieId") int id, Model model){
        return "redirect:/watchlist/adminMovieList";
    }

    @GetMapping("adminUpdateUser")
    public String adminUpdateUser(@RequestParam("userId") int id, Model model){
        return "admin-user-update";
    }

    @GetMapping("/adminDeleteUser")
    public String adminDeleteUser(@RequestParam("userId") int id, Model model){
        return "redirect:/watchlist/adminUserList";
    }

    @GetMapping("/updateMovieById")
    public String updateMovieById(@RequestParam("movieId") int id, Model model){
        return "redirect:/watchlist/adminMovieList";
    }

    @GetMapping("/updateUserById")
    public String updateUserById(@RequestParam("userId") int id, Model model){
        return "redirect:/watchlist/adminUserList";
    }















    /*
    @Secured("USER")
    @GetMapping("/listAllWatchLists")
    public String listAllWatchLists() {

       return "";
    }

    @Secured("USER")
    @GetMapping("/listWatchListByID")
    public String listWatchListByID(){
       return "";
    }

   @Secured("USER")
   @GetMapping("/findWatchListByName")
    public String findWatchListByName(){
    return "";
    }

   @Secured("USER")
   @GetMapping("/findActor")
    public String findActor(){
    return "";
    }

   @Secured("USER")
    @GetMapping("/findRegisseur")
    public String findRegisseur(){
    return "";
    }

    @Secured("USER")
    @GetMapping("/findMovieWithTitle")
    public String findMovieWithTitle(){
     return "";
    }

    @Secured("USER")
    @PostMapping("/createWatchList")
    public String createWatchList(){
     return "";
    }

    @Secured("USER")
    @PostMapping("/addToWatchList")
    public String addToWatchList(){
     return "";
    }


    @Secured("USER")
    @PostMapping("/commentMovieByID")
    public String commentMovieByID(){
     return "";
    }

    @Secured("USER")
    @GetMapping("/listAllComments")
    public String listAllComments(){
     return "";
    }

    @Secured("USER")
    @DeleteMapping("/deleteCommentByID")
    public String deleteCommentByID(){
     return "";
    }

    @Secured("USER")
    @DeleteMapping("/deleteAllComments")
    public String deleteAllComments(){
     return "";
    }

    @Secured("USER")
    @DeleteMapping("/deleteFromWatchList")
    public String deleteFromWatchList(){
     return "";
    }

    @Secured("USER")
    @DeleteMapping("/deleteAllFromWatchList")
    public String deleteAllFromWatchList(){
     return "";
    }

    @Secured("USER")
   @DeleteMapping("/deleteWatchListByID")
    public String deleteWatchListByID(){
     return "";
    }

    @Secured("USER")
   @DeleteMapping("/deleteAllWatchLists")
    public String deleteAllWatchLists(){
     return "";
    }

    @Secured("USER")
    @PostMapping("/addUser")
    public String addUser(){
     return "";
    }

    @Secured("USER")
    @PostMapping("/login")
    public String login(){
     return "";
    }

    @Secured("USER")
   @PostMapping("/checkToken")
    public String checkToken(){
     return "";
    }

    @Secured("USER")
   @DeleteMapping("/deleteUser")
    public String deleteUser(){
     return "";
    }

    @Secured("USER")
    @PostMapping("/logout")
    public String logout(){
     return "";
    }

    //Admin Methods

    @Secured("ADMIN")
    @DeleteMapping("/adminDeleteUser")
    public String adminDeleteUser(){
     return "";
    }

    @Secured("ADMIN")
    @DeleteMapping("/adminDeleteWactchList")
    public String adminDeleteWatchList(){
     return "";
    }

    @Secured("ADMIN")
    @DeleteMapping("/adminDeleteComment")
    public String adminDeleteComment(){
     return "";
    }

    @Secured("ADMIN")
    @GetMapping("/adminFindAllWactchLists")
    public String adminFindAllWatchLists(){
     return "";
    }

    @Secured("ADMIN")
   @GetMapping("/adminFindAllComments")
    public String adminFindAllComments(){
     return "";
    }

    @Secured("ADMIN")
   @PostMapping("/adminAddMovie")
    public String adminAddMovie(){
     return "";
    }

    @Secured("ADMIN")
   @DeleteMapping("/adminRemoveMovie")
    public String adminRemoveMovie(){
     return "";
    }

    @Secured("ADMIN")
    @PostMapping("/adminAddActor")
    public String adminAddActor(){
     return "";
    }

    @Secured("ADMIN")
    @PutMapping("/adminUpdateMovie")
    public String adminUpdateMovie(){
     return "";
    }

    @Secured("ADMIN")
   @PostMapping("/adminAddWatchList")
    public String adminAddWatchList(){
     return "";
    }

    @Secured("ADMIN")
    @PostMapping("/adminAddUser")
    public String adminAddUser(){
     return "";
    }

    @Secured("ADMIN")
    @PostMapping("/adminAddComment")
    public String adminAddComment(){
     return "";
    }

     */
}
