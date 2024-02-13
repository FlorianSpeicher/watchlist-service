package com.example.microservices.watchlistservice.repositories;

import com.example.microservices.watchlistservice.entity.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListRepository extends JpaRepository<WatchList, Integer> {
}
