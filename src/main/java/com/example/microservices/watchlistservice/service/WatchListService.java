package com.example.microservices.watchlistservice.service;

import com.example.microservices.watchlistservice.dto.Actor;
import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.dto.Regisseur;
import com.example.microservices.watchlistservice.entity.Role;
import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.entity.WatchList;
import com.example.microservices.watchlistservice.repositories.RoleRepository;
import com.example.microservices.watchlistservice.repositories.UserRepository;
import com.example.microservices.watchlistservice.repositories.WatchListRepository;
import static com.example.microservices.watchlistservice.utils.StringConverter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WatchListService implements WatchListServiceInterface{


    private final UserRepository userRepository;
    private final WatchListRepository watchListRepository;
    private final RoleRepository roleRepository;

    private final WebClient webClientMovie;
    private final WebClient webClientAuth;

    @Autowired
    public WatchListService(UserRepository userRepository, WatchListRepository watchListRepository, RoleRepository roleRepository, WebClient.Builder webClientBuilderMovie, WebClient.Builder webClientBuilderAuth ){
        this.userRepository = userRepository;
        this.watchListRepository = watchListRepository;
        this.roleRepository = roleRepository;
        this.webClientMovie = webClientBuilderMovie.baseUrl("http://localhost:3307/movies").build();
        this.webClientAuth = webClientBuilderAuth.baseUrl("http://localhost:3308/auth/token").build();
    }

    @Override
    public String generateToken() {
        return webClientAuth.get().uri("/generate").retrieve().bodyToMono(String.class).block();
    }

    @Override
    public String validateAndUpdateToken(String token) {
        return webClientAuth.post().uri("/validateAndUpdate").bodyValue(token).retrieve().bodyToMono(String.class).block();
    }

    @Override
    public void invalidateToken(String user) {
        webClientAuth.delete().uri("/invalidate");
    }

    @Override
    public List<Actor> findAllActors() {
        return stringToActorList(webClientMovie.get().uri("/list/actors/all").retrieve().bodyToMono(String.class).block());
    }

    @Override
    public List<Regisseur> findAllRegisseurs() {
        return stringToRegisseurList(webClientMovie.get().uri("/list/regisseurs/all").retrieve().bodyToMono(String.class).block());
    }

    @Override
    public void saveUser(User currentUser) {
        userRepository.save(currentUser);
    }

    @Override
    public Movie findMovieById(int id) {
        return stringToMovie(webClientMovie.get().uri("/list/{id}", id).retrieve().bodyToMono(String.class).block());
    }

    @Override
    public WatchList findWatchListById(int watchListId) {
        Optional<WatchList> watchList = watchListRepository.findById(watchListId);
        return watchList.orElse(null);
    }

    @Override
    public void saveWatchList(WatchList watchList) {
        watchListRepository.save(watchList);
    }

    @Override
    public void saveMovie(Movie movie) {
        webClientMovie.put().uri("/update/{id}", movie.getId()).bodyValue(movieToString(movie));
    }

    @Override
    public Regisseur findRegisseurById(int id) {
        return stringToRegisseur(webClientMovie.get().uri("/list/id/regisseur/{id}", id).retrieve().bodyToMono(String.class).block());
    }

    @Override
    public Actor findActorById(int id) {
        return stringToActor(webClientMovie.get().uri("/list/id/actor/{id}", id).retrieve().bodyToMono(String.class).block());
    }

    @Override
    public void deleteUser(User currentUser) {
        userRepository.deleteById(currentUser.getId());
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addCommentToMovie(int id, String comment) {
        webClientMovie.post().uri("/addReview/{id}", id).bodyValue(stringToCommentString(comment));
    }

    @Override
    public User findUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public List<Movie> findAllMovies() {
        return stringToMovieList(webClientMovie.get().uri("/list").retrieve().bodyToMono(String.class).block());
    }

    @Override
    public List<WatchList> findAllWatchListsByUser(User currentUser) {
        List<WatchList> allWatchLists = watchListRepository.findAll();
        List<WatchList> userWatchlist = new ArrayList<>();
        for(WatchList watchList: allWatchLists){
            if (Objects.equals(watchList.getUserName(), currentUser.getUserName())){
                userWatchlist.add(watchList);
            }
        }
        return userWatchlist;
    }

    @Override
    public void deleteMovieById(int id) {
        webClientMovie.delete().uri("/delete/{id}", id);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Movie> getMoviesByActorId(int id) {
        return stringToMovieList(webClientMovie.get().uri("/list/actor/{id}", id).retrieve().bodyToMono(String.class).block());
    }

    @Override
    public List<Movie> getMoviesByRegisseurId(int id) {
        return stringToMovieList(webClientMovie.get().uri("/list/regisseur/{id}", id).retrieve().bodyToMono(String.class).block());
    }

    @Override
    public void deleteMovieFromWatchList(int watchListId, int movieId) {
        Optional<WatchList> watchListOptional = watchListRepository.findById(watchListId);
        WatchList watchList = watchListOptional.orElse(null);
       // List<Movie> allMovies = Objects.requireNonNull(watchList).getMovies();
        List<Integer> allMovies = Objects.requireNonNull(watchList).getMovies();
        Movie movie = findMovieById(movieId);
        allMovies.remove(movie);
        watchList.setMovies(allMovies);
        watchListRepository.save(watchList);
    }

    @Override
    public void deleteWatchList(int id) {
        watchListRepository.deleteById(id);
    }

    @Override
    public User findUserByUserName(String name) {
        List<User> allUsers = userRepository.findAll();
        System.out.println("WatchListService: findUserByUsername: ");
        System.out.println(name);
        System.out.println(allUsers + "\n\n");
        for (User user: allUsers){
            if (Objects.equals(user.getUserName(), name)){
                return user;
            }
        }

        return null;
    }

    @Override
    public Role findRoleById(int id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        System.out.println("Id von Role:" + id);
        Role role = roleOptional.orElse(null);
        return role;
    }

}
