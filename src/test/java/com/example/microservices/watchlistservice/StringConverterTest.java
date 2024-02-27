package com.example.microservices.watchlistservice;

import static org.junit.jupiter.api.Assertions.*;
import static com.example.microservices.watchlistservice.utils.StringConverter.*;

import com.example.microservices.watchlistservice.dto.Actor;
import com.example.microservices.watchlistservice.dto.Review;
import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.dto.Regisseur;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@DisplayName("StringConverterTest: ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class StringConverterTest {
    @Test
    @Order(1)
    void stringToMovieTest(){
        String testString = "{\"id\":3, \"title\":\"Star Wars: Rückkehr der Jedi-Ritter\", \"length\":\"03:00:00\", \"episodes\":6, \"ageRestriction\":12, \"regisseur\":{\"id\":3, \"firstName\":\"Richard\", \"lastName\":\"Marquand\"}, \"reviews\":[{\"id\":3, \"comment\":\"Well playing actors\"}], \"actors\":[{\"id\":11, \"firstName\":\"Mark\", \"lastName\":\"Hamill\"}, {\"id\":12, \"firstName\":\"Harrison\", \"lastName\":\"Ford\"}]}";

        Regisseur regisseur = new Regisseur("Richard", "Marquand");
        regisseur.setId(3);
        Actor actor1 = new Actor("Mark", "Hamill");
        actor1.setId(11);
        Actor actor2 = new Actor("Harrison", "Ford");
        actor2.setId(12);
        Review comment = new Review("Well playing actors");
        comment.setId(3);
        Movie sollMovie = new Movie("Star Wars: Rückkehr der Jedi-Ritter", "03:00:00", 12, 6);
        sollMovie.setId(3);
        sollMovie.setRegisseur(regisseur);
        sollMovie.addActor(actor1);
        sollMovie.addActor(actor2);
        sollMovie.addComment(comment);

        Movie resultMovie = stringToMovie(testString);

        assertEquals(sollMovie.toString(), resultMovie.toString());
    }

    @Test
    @Order(2)
    void stringToActorListTest(){
        String input = "[{\"id\":1, \"firstName\":\"Ewan\", \"lastName\":\"McGregor\"}, {\"id\":2, \"firstName\":\"Liam\", \"lastName\":\"Neeson\"}, {\"id\":3, \"firstName\":\"Natalie\", \"lastName\":\"Portman\"}, {\"id\":4, \"firstName\":\"Hayden\", \"lastName\":\"Christensen\"}, {\"id\":5, \"firstName\":\"Jimmy\", \"lastName\":\"Smits\"}, {\"id\":6, \"firstName\":\"Anthony\", \"lastName\":\"Daniels\"}, {\"id\":7, \"firstName\":\"Alden\", \"lastName\":\"Ehrenreich\"}, {\"id\":8, \"firstName\":\"Woody\", \"lastName\":\"Harrelson\"}, {\"id\":9, \"firstName\":\"Diego\", \"lastName\":\"Luna\"}, {\"id\":10, \"firstName\":\"Ben\", \"lastName\":\"Mendelsohn\"}, {\"id\":11, \"firstName\":\"Mark\", \"lastName\":\"Hamill\"}, {\"id\":12, \"firstName\":\"Harrison\", \"lastName\":\"Ford\"}, {\"id\":13, \"firstName\":\"Carrie\", \"lastName\":\"Fisher\"}, {\"id\":14, \"firstName\":\"Kenny\", \"lastName\":\"Baker\"}, {\"id\":15, \"firstName\":\"David\", \"lastName\":\"Prowse\"}, {\"id\":16, \"firstName\":\"Frank\", \"lastName\":\"Oz\"}, {\"id\":17, \"firstName\":\"Daisy\", \"lastName\":\"Ridley\"}, {\"id\":18, \"firstName\":\"Adam\", \"lastName\":\"Driver\"}]";
        List<Actor> soll = new ArrayList<>();
        Actor actor1 = new Actor(1, "Ewan", "McGregor");
        Actor actor2 = new Actor(2, "Liam", "Neeson");
        Actor actor3 = new Actor(3, "Natalie", "Portman");
        Actor actor4 = new Actor(4, "Hayden", "Christensen");
        Actor actor5 = new Actor(5, "Jimmy", "Smits");
        Actor actor6 = new Actor(6, "Anthony", "Daniels");
        Actor actor7 = new Actor(7, "Alden", "Ehrenreich");
        Actor actor8 = new Actor(8, "Woody", "Harrelson");
        Actor actor9 = new Actor(9, "Diego", "Luna");
        Actor actor10 = new Actor(10, "Ben", "Mendelsohn");
        Actor actor11 = new Actor(11, "Mark", "Hamill");
        Actor actor12 = new Actor(12, "Harrison", "Ford");
        Actor actor13 = new Actor(13, "Carrie", "Fisher");
        Actor actor14 = new Actor(14, "Kenny", "Baker");
        Actor actor15 = new Actor(15, "David", "Prowse");
        Actor actor16 = new Actor(16, "Frank", "Oz");
        Actor actor17 = new Actor(17, "Daisy", "Ridley");
        Actor actor18 = new Actor(18, "Adam", "Driver");

        soll.add(actor1);
        soll.add(actor2);
        soll.add(actor3);
        soll.add(actor4);
        soll.add(actor5);
        soll.add(actor6);
        soll.add(actor7);
        soll.add(actor8);
        soll.add(actor9);
        soll.add(actor10);
        soll.add(actor11);
        soll.add(actor12);
        soll.add(actor13);
        soll.add(actor14);
        soll.add(actor15);
        soll.add(actor16);
        soll.add(actor17);
        soll.add(actor18);

        List<Actor> result = stringToActorList(input);

        assertEquals(soll.toString(), result.toString());
    }

    @Test
    @Order(3)
    void stringToRegisseurListTest(){
        String input = "[{\"id\":1, \"firstName\":\"George\", \"lastName\":\"Lucas\"}, {\"id\":2, \"firstName\":\"Irvin\", \"lastName\":\"Kershner\"}, {\"id\":3, \"firstName\":\"Richard\", \"lastName\":\"Marquand\"}, {\"id\":4, \"firstName\":\"J.J.\", \"lastName\":\"Abrams\"}, {\"id\":5, \"firstName\":\"Rian\", \"lastName\":\"Johnson\"}]";
        List<Regisseur> soll = new ArrayList<>();
        soll.add(new Regisseur(1, "George", "Lucas"));
        soll.add(new Regisseur(2, "Irvin", "Kershner"));
        soll.add(new Regisseur(3, "Richard", "Marquand"));
        soll.add(new Regisseur(4, "J.J.", "Abrams"));
        soll.add(new Regisseur(5, "Rian", "Johnson"));
        List<Regisseur> result = stringToRegisseurList(input);
        assertEquals(soll.toString(), result.toString());
    }
    @Test
    @Order(4)
    void stringToCommentStringTest(){
        String input = "Dies ist ein Kommentar";
        String soll = "{\"comment\":\"Dies ist ein Kommentar\"}";
        String result = stringToCommentString(input);
        assertEquals(soll, result);
    }


    @Test
    @Order(5)
    void stringToMovieListTest(){
        String input = "[{\"id\":7, \"title\":\"Star Wars: Das Erwachen der Macht\", \"length\":\"03:00:00\", \"episodes\":7, \"ageRestriction\":12, \"regisseur\":{\"id\":4, \"firstName\":\"J.J.\", \"lastName\":\"Abrams\"}, \"reviews\":[{\"id\":7, \"comment\":\"Not so good\"}], \"actors\":[{\"id\":13, \"firstName\":\"Carrie\", \"lastName\":\"Fisher\"}, {\"id\":14, \"firstName\":\"Kenny\", \"lastName\":\"Baker\"}]}, {\"id\":9, \"title\":\"Star Wars: Der Aufstieg Skywalkers\", \"length\":\"03:00:00\", \"episodes\":9, \"ageRestriction\":16, \"regisseur\":{\"id\":4, \"firstName\":\"J.J.\", \"lastName\":\"Abrams\"}, \"reviews\":[{\"id\":9, \"comment\":\"Good but the others were better\"}], \"actors\":[{\"id\":17, \"firstName\":\"Daisy\", \"lastName\":\"Ridley\"}, {\"id\":18, \"firstName\":\"Adam\", \"lastName\":\"Driver\"}]}]";
        List<Movie> soll = new ArrayList<>();
        List<Review> commentList1 = new ArrayList<>();
        commentList1.add(new Review(7, "Not so good"));
        List<Actor> actorList1 = new ArrayList<>();
        actorList1.add(new Actor(13, "Carrie", "Fisher"));
        actorList1.add(new Actor(14, "Kenny", "Baker"));
        soll.add(new Movie(7, "Star Wars: Das Erwachen der Macht", "03:00:00", 7, 12, new Regisseur(4, "J.J.", "Abrams"), commentList1, actorList1));

        List<Review> commentList2 = new ArrayList<>();
        commentList2.add(new Review(9, "Good but the others were better"));
        List<Actor> actorList2 = new ArrayList<>();
        actorList2.add(new Actor(17, "Daisy", "Ridley"));
        actorList2.add(new Actor(18, "Adam", "Driver"));
        soll.add(new Movie(9, "Star Wars: Der Aufstieg Skywalkers", "03:00:00", 9, 16, new Regisseur(4, "J.J.", "Abrams"), commentList2, actorList2));

        List<Movie> result = stringToMovieList(input);
        assertEquals(soll.toString(), result.toString());
    }

    @Test
    @Order(6)
    void movieToStringTest(){
        List<Review> commentList1 = new ArrayList<>();
        commentList1.add(new Review(7, "Not so good"));
        List<Actor> actorList1 = new ArrayList<>();
        actorList1.add(new Actor(13, "Carrie", "Fisher"));
        actorList1.add(new Actor(14, "Kenny", "Baker"));
        Movie input = new Movie(7, "Star Wars: Das Erwachen der Macht", "03:00:00", 7, 12, new Regisseur(4, "J.J.", "Abrams"), commentList1, actorList1);
        String soll = "{\"id\":7,\"title\":\"Star Wars: Das Erwachen der Macht\",\"length\":\"03:00:00\",\"episodes\":7,\"ageRestriction\":12,\"regisseur\":{\"id\":4,\"firstName\":\"J.J.\",\"lastName\":\"Abrams\"},\"reviews\":[{\"id\":7,\"comment\":\"Not so good\"}],\"actors\":[{\"id\":13,\"firstName\":\"Carrie\",\"lastName\":\"Fisher\"},{\"id\":14,\"firstName\":\"Kenny\",\"lastName\":\"Baker\"}]}";

        String result = movieToString(input);

        assertEquals(soll, result);
    }

    @Test
    @Order(7)
    void stringToRegisseurTest(){
        String input = "{\"id\":1, \"firstName\":\"George\", \"lastName\":\"Lucas\"}";
        Regisseur soll = new Regisseur(1, "George", "Lucas");
        Regisseur result = stringToRegisseur(input);

        assertEquals(soll.toString(), result.toString());
    }

    @Test
    @Order(8)
    void stringToActorTest(){
        String input = "{\"id\":7, \"firstName\":\"Alden\", \"lastName\":\"Ehrenreich\"}";
        Regisseur soll = new Regisseur(7, "Alden", "Ehrenreich");
        Regisseur result = stringToRegisseur(input);

        assertEquals(soll.toString(), result.toString());
    }
}
