package com.example.microservices.watchlistservice.controller;

import com.example.microservices.watchlistservice.service.WatchListService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/watchlist")
public class WatchListController {

    private WatchListService watchListService;
    private final String movieNoEntryResponse = "No Entry!";
    private final String authNoEntryResponse = "nonValidToken";

    public WatchListController(WatchListService watchListService){
        this.watchListService = watchListService;
    }
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
}
