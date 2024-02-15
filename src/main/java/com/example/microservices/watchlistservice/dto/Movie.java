package com.example.microservices.watchlistservice.dto;

import jakarta.persistence.Entity;

@Entity
public class Movie {
    private int id;
    private String title;
    private String length;
    private int ageRestriction;
    private int episodes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public Movie(String title, String length, int ageRestriction, int episodes) {
        this.title = title;
        this.length = length;
        this.ageRestriction = ageRestriction;
        this.episodes = episodes;
    }
}
