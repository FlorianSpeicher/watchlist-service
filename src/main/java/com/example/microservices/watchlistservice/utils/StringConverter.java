package com.example.microservices.watchlistservice.utils;

import com.example.microservices.watchlistservice.dto.Actor;
import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.dto.Regisseur;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public abstract class StringConverter {

    static ObjectMapper om = new ObjectMapper();

   public static Movie stringToMovie(String input) {
       Movie movie;
       try {
           movie = om.readValue(input, Movie.class);
       } catch (JsonProcessingException e) {
           System.err.println("JsonProcessingException in method: stringToMovie");
           return null;
       }
       return movie;
   }

   public static List<Actor> stringToActorList(String input){
       List<Actor> actorList;
       try {
           actorList = om.readValue(input, new TypeReference<List<Actor>>() {});
       } catch (JsonProcessingException e) {
           System.err.println("JsonProcessingException in method: stringToActorList");
           return null;
       }
       return actorList;
   }

   public static List<Regisseur> stringToRegisseurList(String input){
       List<Regisseur> regisseurList;
       try {
           regisseurList = om.readValue(input, new TypeReference<List<Regisseur>>() {});
       } catch (JsonProcessingException e) {
           System.err.println("JsonProcessingException in method: stringToRegisseurList");
           return null;
       }
       return regisseurList;
   }

   public static String stringToCommentString(String input){
       return "{\"comment\":\"" + input + "\"}";
   }

   public static List<Movie> stringToMovieList(String input){
       List<Movie> movieList;
       try {
           movieList = om.readValue(input, new TypeReference<List<Movie>>() {});
       } catch (JsonProcessingException e) {
           System.err.println("JsonProcessingException in method: stringToMovieList");
           return null;
       }
       return movieList;
   }

   public static String movieToString(Movie movie){
       try {
           return om.writeValueAsString(movie);
       } catch (JsonProcessingException e) {
           System.err.println("JsonProcessingException in method: movieToString");
           return null;
       }
   }

   public static Regisseur stringToRegisseur(String input){
       Regisseur regisseur;
       try {
           regisseur = om.readValue(input, Regisseur.class);
       } catch (JsonProcessingException e) {
           System.err.println("JsonProcessingException in method: stringToRegisseur");
           return null;
       }
       return regisseur;
   }

    public static Actor stringToActor(String input){
        Actor actor;
        try {
            actor = om.readValue(input, Actor.class);
        } catch (JsonProcessingException e) {
            System.err.println("JsonProcessingException in method: stringToActor");
            return null;
        }
        return actor;
    }

}
