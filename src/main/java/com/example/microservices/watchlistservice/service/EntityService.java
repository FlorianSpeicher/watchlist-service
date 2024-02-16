package com.example.microservices.watchlistservice.service;

import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.entity.WatchList;
import com.example.microservices.watchlistservice.repositories.UserRepository;
import com.example.microservices.watchlistservice.repositories.WatchListRepository;

import java.util.List;
import java.util.Objects;

public class EntityService implements EntityServiceInterface{

    private UserRepository userRepository;
    private WatchListRepository watchListRepository;
    @Override
    public boolean isUserNameInUse(String name) {
        List<User> allUsers = userRepository.findAll();
        for(User user: allUsers){
            if(Objects.equals(user.getUserName(), name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isWatchListNameInUse(String name) {
        List<WatchList> allWatchLists = watchListRepository.findAll();
        for(WatchList watchList: allWatchLists){
            if(Objects.equals(watchList.getName(), name)){
                return true;
            }
        }
        return false;
    }

}
