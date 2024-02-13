package com.example.microservices.watchlistservice.repositories;

import com.example.microservices.watchlistservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
