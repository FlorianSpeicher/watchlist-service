package com.example.microservices.watchlistservice.service;

import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.entity.WatchList;
import com.example.microservices.watchlistservice.repositories.UserRepository;
import com.example.microservices.watchlistservice.repositories.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EntityService implements EntityServiceInterface{

    @Autowired
    private UserRepository userRepository;
    @Autowired
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

    @Override
    public User saveUser(User user) {
        User userModel = populateUserData(user);
        return userRepository.save(userModel);
    }

    @Override
    public User populateUserData(User user) {
        User newUser = user;
        newUser.setPassword(user.getPassword());
        return newUser;
    }

}
