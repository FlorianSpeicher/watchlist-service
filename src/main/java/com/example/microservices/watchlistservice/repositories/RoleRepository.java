package com.example.microservices.watchlistservice.repositories;

import com.example.microservices.watchlistservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
