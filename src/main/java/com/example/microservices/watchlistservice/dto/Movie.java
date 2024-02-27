package com.example.microservices.watchlistservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Movie {
    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("length")
    private String length;
    @JsonProperty("episodes")
    private int episodes;
    @JsonProperty("ageRestriction")
    private int ageRestriction;
    @JsonProperty("regisseur")
    private Regisseur regisseur;
    @JsonProperty("reviews")
    private List<Review> reviews;
    @JsonProperty("actors")
    private List<Actor> actor;

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

    public Regisseur getRegisseur() {
        return regisseur;
    }

    public void setRegisseur(Regisseur regisseur) {
        this.regisseur = regisseur;
    }

    public List<Actor> getActor() {
        return actor;
    }

    public void setActor(List<Actor> actor) {
        this.actor = actor;
    }

    public void addActor(Actor actor) {
        this.actor.add(actor);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addComment(Review comment){
        this.reviews.add(comment);
    }



    public Movie(String title, String length, int ageRestriction, int episodes) {
        this.title = title;
        this.length = length;
        this.ageRestriction = ageRestriction;
        this.episodes = episodes;
        this.actor = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public Movie(int id, String title, String length, int episodes, int ageRestriction, Regisseur regisseur, List<Review> comments, List<Actor> actors){
        this(title, length, ageRestriction, episodes);
        this.regisseur = regisseur;
        this.actor = actors;
        this.id = id;
        this.reviews = comments;

    }
    public Movie(){}

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", length='" + length + '\'' +
                ", ageRestriction=" + ageRestriction +
                ", episodes=" + episodes +
                ", regisseur=" + regisseur +
                ", actor=" + actor +
                ", reviews=" + reviews +
                '}';
    }
}
