package com.example.microservices.watchlistservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
public class Review {
    @JsonProperty("id")
    private int id;
    @JsonProperty("comment")
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Review(String comment) {
        this.comment = comment;
    }

    public Review(int id, String comment){
        this(comment);
        this.id = id;
    }

    public Review(){}

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
