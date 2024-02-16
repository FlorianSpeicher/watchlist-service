package com.example.microservices.watchlistservice.dto;

import jakarta.persistence.Entity;


@Entity
public class Actor {

    private int id;
    private final String firstName;
    private final String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
