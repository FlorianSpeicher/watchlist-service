package com.example.microservices.watchlistservice.utils;

import java.util.List;

public abstract class StringConverter {

    public static String toMovie(String title, String length, int episodes, int ageRestriction){
        return "{\"title\":\"" + title + "\", \"length\":\"" + length + "\", \"episodes\":" + episodes + ", \"ageRestriction\":" + ageRestriction + "}";
    }
    public static String toPerson(String firstName, String lastName){
        return "{\"fistName\":\"" + firstName + "\", \"lastName\":\"" + lastName + "\"}";
    }

    public static String toComment(String comment){
        return "{\"comment\":\"" + comment + "\"}";
    }

    //? methode relevant?
    public static String toWatchList(String name, List<Integer> idList){
        String base = "{\"name\":\"" + name + "\", \"movies\":[";
        return null;
    }

    public static List<String> toMovieStringList(String input){

        return null;
    }

}
