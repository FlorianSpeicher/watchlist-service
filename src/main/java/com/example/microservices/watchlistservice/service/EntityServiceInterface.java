package com.example.microservices.watchlistservice.service;

import com.example.microservices.watchlistservice.entity.User;

public interface EntityServiceInterface {
    boolean isUserNameInUse(String name);
    boolean isWatchListNameInUse(String name);
    User findUserByUserName(String name);

}
