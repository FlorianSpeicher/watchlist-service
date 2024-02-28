package com.example.microservices.watchlistservice.repositories;

import com.example.microservices.watchlistservice.entity.MovieWatchListConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieWatchListConnectionRepository extends JpaRepository<MovieWatchListConnection, Integer> {
}
