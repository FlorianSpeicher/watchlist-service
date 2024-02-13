package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.service.WatchListService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/watchlist")
public class WatchlistController {

    private WatchListService watchListService;

    public WatchlistController(WatchListService watchListService){
        this.watchListService = watchListService;
    }

   @GetMapping("/listAllWatchLists")
    public String listAllWatchLists() {
    }

    @GetMapping("/listWatchListByID")
    public String listWatchListByID(){

    }

   @GetMapping("/findWatchListByName")
    public String findWatchListByName(){

    }

   @GetMapping("/findActor")
    public String findActor(){

    }

    @GetMapping("/findRegisseur")
    public String findRegisseur(){

    }

    @GetMapping("/findMovieWithTitle")
    public String findMovieWithTitle(){

    }

    @PostMapping("/createWatchList")
    public String createWatchList(){

    }

    @PostMapping("/addToWatchList")
    public String addToWatchList(){

    }

    @PostMapping("/commentMovieByID")
    public String commentMovieByID(){

    }

    @GetMapping("/listAllComments")
    public String listAllComments(){

    }

    @DeleteMapping("/deleteCommentByID")
    public String deleteCommentByID(){

    }

    @DeleteMapping("/deleteAllComments")
    public String deleteAllComments(){

    }

    @DeleteMapping("/deleteFromWatchList")
    public String deleteFromWatchList(){

    }

    @DeleteMapping("/deleteAllFromWatchList")
    public String deleteAllFromWatchList(){

    }

   @DeleteMapping("/deleteWatchListByID")
    public String deleteWatchListByID(){

    }

   @DeleteMapping("/deleteAllWatchLists")
    public String deleteAllWatchLists(){

    }

    @PostMapping("/addUser")
    public String addUser(){

    }

    @PostMapping("/login")
    public String login(){

    }

   @PostMapping("/checkToken")
    public String checkToken(){

    }

   @DeleteMapping("/deleteUser")
    public String deleteUser(){

    }

    @PostMapping("/logout")
    public String logout(){

    }

    //Admin Methods

    @DeleteMapping("/adminDeleteUser")
    public String adminDeleteUser(){

    }

    @DeleteMapping("/adminDeleteWactchList")
    public String adminDeleteWatchList(){

    }

    @DeleteMapping("/adminDeleteComment")
    public String adminDeleteComment(){

    }

    @GetMapping("/adminFindAllWactchLists")
    public String adminFindAllWatchLists(){

    }

   @GetMapping("/adminFindAllComments")
    public String adminFindAllComments(){

    }

   @PostMapping("/adminAddMovie")
    public String adminAddMovie(){

    }

   @DeleteMapping("/adminRemoveMovie")
    public String adminRemoveMovie(){

    }

    @PostMapping("/adminAddActor")
    public String adminAddActor(){

    }

    @PutMapping("/adminUpdateMovie")
    public String adminUpdateMovie(){

    }

   @PostMapping("/adminAddWatchList")
    public String adminAddWatchList(){

    }

    @PostMapping("/adminAddUser")
    public String adminAddUser(){

    }

    @PostMapping("/adminAddComment")
    public String adminAddComment(){

    }
}
