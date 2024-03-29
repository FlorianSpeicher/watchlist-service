package com.example.microservices.watchlistservice.repositories;

import com.example.microservices.watchlistservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
    boolean existsByuserName(String userName);
}
