package com.example.microservices.watchlistservice.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "watchlist")
public class WatchList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;

    private List<Integer> movies;


    public String getName(){return this.name;}
}
