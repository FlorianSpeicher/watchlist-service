package com.example.microservices.watchlistservice.service;

import com.example.microservices.watchlistservice.dto.Actor;
import com.example.microservices.watchlistservice.dto.Movie;
import com.example.microservices.watchlistservice.dto.Regisseur;
import com.example.microservices.watchlistservice.entity.MovieWatchListConnection;
import com.example.microservices.watchlistservice.entity.Role;
import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.entity.WatchList;
import com.example.microservices.watchlistservice.repositories.MovieWatchListConnectionRepository;
import com.example.microservices.watchlistservice.repositories.RoleRepository;
import com.example.microservices.watchlistservice.repositories.UserRepository;
import com.example.microservices.watchlistservice.repositories.WatchListRepository;
import static com.example.microservices.watchlistservice.utils.StringConverter.*;

import com.example.microservices.watchlistservice.utils.watchlist.ValidWatchlistName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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

    private final MovieWatchListConnectionRepository movieWatchListConnectionRepository;

    private final WebClient webClientMovie;
    private final WebClient webClientAuth;

    @Autowired
    public WatchListService(UserRepository userRepository, WatchListRepository watchListRepository, RoleRepository roleRepository, MovieWatchListConnectionRepository movieWatchListConnectionRepository, WebClient.Builder webClientBuilderMovie, WebClient.Builder webClientBuilderAuth ){
        this.userRepository = userRepository;
        this.watchListRepository = watchListRepository;
        this.roleRepository = roleRepository;
        this.movieWatchListConnectionRepository = movieWatchListConnectionRepository;
        this.webClientMovie = webClientBuilderMovie.baseUrl("http://localhost:3307/movies").build();
        this.webClientAuth = webClientBuilderAuth.baseUrl("http://localhost:3308/auth/token").build();
    }

    @Override
    public String generateToken() {
        return webClientAuth.get().uri("/generate").retrieve().bodyToMono(String.class).block();
    }

    @Override
    public String validateAndUpdateToken(String token) {
        return webClientAuth.method(HttpMethod.GET).uri("/validateAndUpdate").bodyValue(token).retrieve().bodyToMono(String.class).block();
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
        return stringToRegisseur(webClientMovie.get().uri("/list/id/onlyregisseur/{id}", id).retrieve().bodyToMono(String.class).block());
    }

    @Override
    public Actor findActorById(int id) {
        return stringToActor(webClientMovie.get().uri("/list/id/onlyactor/{id}", id).retrieve().bodyToMono(String.class).block());
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
    public List<Movie> findAllMoviesOfWatchList(int id) {
        List<MovieWatchListConnection> connections = movieWatchListConnectionRepository.findAll();
        List<Movie> movieList = new ArrayList<>();
        for (MovieWatchListConnection con: connections) {
            if (con.getMovieId() == id){
                movieList.add(findMovieById(id));
            }
        }
        return movieList;
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
        return stringToMovieList(webClientMovie.get().uri("/list/id/actor/{id}", id).retrieve().bodyToMono(String.class).block());
    }

    @Override
    public List<Movie> getMoviesByRegisseurId(int id) {
        return stringToMovieList(webClientMovie.get().uri("/list/id/regisseur/{id}", id).retrieve().bodyToMono(String.class).block());
    }

    @Override
    public void deleteMovieFromWatchList(int watchListId, int movieId) {
        List<MovieWatchListConnection> list = movieWatchListConnectionRepository.findAll();
        for (MovieWatchListConnection con: list) {
            if ((con.getWatchListId() == watchListId && con.getMovieId() == movieId)){
                movieWatchListConnectionRepository.delete(con);
            }
        }
    }

    @Override
    public void deleteWatchList(int id) {
        watchListRepository.deleteById(id);
    }

    @Override
    public User findUserByUserName(String name) {
        List<User> allUsers = userRepository.findAll();
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
        Role role = roleOptional.orElse(null);
        return role;
    }

    @Override
    public List<WatchList> findAllWatchListsOfUser(int id) {
        return watchListRepository.findByUser(id);
    }

    @Override
    public void addMovieToWatchList(MovieWatchListConnection connection) {
        System.out.println("addMovieToWatchList wird aufgerufen im Service");
        movieWatchListConnectionRepository.save(connection);
    }

    @Override
    public User findUserByEmail(String name) {
        List<User> allUsers = userRepository.findAll();
        for (User user: allUsers){
            if (Objects.equals(user.getEmail(), name)){
                return user;
            }
        }

        return null;
    }

    public List<WatchList> getWatchLists(int id) {
        return watchListRepository.findByUser(id);
    }

    public void setWatchLists(List<WatchList> watchLists) {
        watchListRepository.saveAll(watchLists);
    }

    @ValidWatchlistName
    public void addWatchLists(WatchList watchList){watchListRepository.save(watchList);}

}
