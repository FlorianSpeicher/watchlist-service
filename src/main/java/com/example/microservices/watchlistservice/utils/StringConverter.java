package com.example.microservices.watchlistservice.utils;

import com.example.microservices.watchlistservice.dto.Actor;
import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.dto.Regisseur;

import java.util.List;

public abstract class StringConverter {

    //TODO implement String Convertion
    //TODO Json Mapper anschauen, ob dieser die Daten richtig in die PObjektform convertiert
   public static Movie stringToMovie(String input){
       return null;
   }

   public static List<Actor> stringToActorList(String input){
       return null;
   }

   public static List<Regisseur> stringToRegisseurList(String input){
       return null;
   }

   public static String stringToCommentString(String input){
       return null;
   }
}
