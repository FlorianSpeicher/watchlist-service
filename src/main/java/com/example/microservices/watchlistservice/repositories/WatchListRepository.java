package com.example.microservices.watchlistservice.repositories;

import com.example.microservices.watchlistservice.entity.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchListRepository extends JpaRepository<WatchList, Integer> {
    List<WatchList> findByUser(int id);
}
