package com.example.microservices.watchlistservice.utils;

import com.example.microservices.watchlistservice.dto.Actor;
import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.dto.Regisseur;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class StringConverter {

    static ObjectMapper om = new ObjectMapper();

   public static Movie stringToMovie(String input) {
       Movie movie;
       try {
           movie = om.readValue(input, Movie.class);
       } catch (JsonProcessingException e) {
           System.err.println("JsonProcessingException in method: stringToMovie");
           throw new RuntimeException(e.getMessage());
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
       System.out.println("stringToMovieList: " + input);
       List<Movie> movieList;
       try {
           movieList = om.readValue(input, new TypeReference<List<Movie>>() {});
       } catch (JsonProcessingException e) {
           System.err.println("JsonProcessingException in method: stringToMovieList");
           return null;
       }
       return movieList;


/*
       List<Movie> allMovies = new ArrayList<>();
       try {
           JsonArray jsonArray = new JsonArray();
           System.out.println("hier wird noch audgeführt");
           if(jsonArray != null){
               System.out.println("Ist Fall");
               System.out.println(jsonArray);
               int len = jsonArray.size();
               for (int i = 0; i < len; i++){
                   System.out.println(om.readValue((JsonParser) jsonArray.get(i), Movie.class));
                   allMovies.add(om.readValue((JsonParser) jsonArray.get(i), Movie.class));
               }
           }
       } catch (JsonProcessingException e) {
           System.err.println("JsonProcessingException in method: stringToMovieList");
           System.out.println(allMovies);
           return null;
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       System.out.println(allMovies);
       return allMovies;
       */
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
