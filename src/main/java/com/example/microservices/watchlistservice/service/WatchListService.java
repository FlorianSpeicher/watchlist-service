package com.example.microservices.watchlistservice.service;

import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.entity.WatchList;
import com.example.microservices.watchlistservice.repositories.RoleRepository;
import com.example.microservices.watchlistservice.repositories.UserRepository;
import com.example.microservices.watchlistservice.repositories.WatchListRepository;
import com.example.microservices.watchlistservice.utils.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WatchListService implements WatchListServiceInterface{


    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private WatchListRepository watchListRepository;

    private final WebClient webClientMovie;
    private final WebClient webClientAuth;

    @Autowired
    public WatchListService(RoleRepository roleRepository, UserRepository userRepository, WatchListRepository watchListRepository, WebClient.Builder webClientBuilderMovie, WebClient.Builder webClientBuilderAuth ){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.watchListRepository = watchListRepository;
        this.webClientMovie = webClientBuilderMovie.baseUrl("http://localhost:8080/movies").build();
        this.webClientAuth = webClientBuilderAuth.baseUrl("http://localhost:8080/auth").build();
    }

    @Override
    public List<WatchList> findAllWatchLists() {
        return watchListRepository.findAll();
    }

    @Override
    public List<String> findAllMovies() {
        String result = webClientMovie.get().uri("/list").retrieve().bodyToMono(String.class).block();
        return StringConverter.toStringList(result);
    }

    @Override
    public WatchList findWatchListByID(int id) {
        Optional<WatchList> watchList = watchListRepository.findById(id);
        WatchList watchList1 = null;
        if(watchList.isPresent()){
            watchList1 = watchList.get();
            return watchList1;
        } else {
            return null;
        }
    }

    @Override
    public String findMovieByID(int id) {
        return webClientMovie.get().uri("/list/id/{id}", id).retrieve().bodyToMono(String.class).block();
    }

    @Override
    public List<WatchList> findWatchListByName(String name) {

        List<WatchList> watchLists = watchListRepository.findAll();
        List<WatchList> watchListsWithName = new ArrayList<>();
        for(WatchList watchList: watchLists){
            if (Objects.equals(name, watchList.getName())){
                watchListsWithName.add(watchList);
            }
        }
        return watchListsWithName;
    }

    @Override
    public String findMovieByTitle(String title) {

        return webClientMovie.method(HttpMethod.GET).uri("/list/title").body(Mono.just(title), String.class)
                .retrieve().bodyToMono(String.class).block();
    }

    @Override
    public String findMovieByActor(String actor) {
        return webClientMovie.method(HttpMethod.GET).uri("/list/actor").body(Mono.just(actor), String.class)
                .retrieve().bodyToMono(String.class).block();
    }

    @Override
    public String findMovieByRegisseur(String regisseur) {
        return webClientMovie.method(HttpMethod.GET).uri("/list/regisseur").body(Mono.just(regisseur), String.class)
                .retrieve().bodyToMono(String.class).block();
    }

    @Override
    public WatchList saveWatchList(WatchList watchList) {
        return watchListRepository.save(watchList);
    }

    @Override
    public String saveComment(int id, String comment) {
        return webClientMovie.method(HttpMethod.POST).uri("/addReview/{id}", id).body(Mono.just(comment), String.class)
                .retrieve().bodyToMono(String.class).block();
    }

    @Override
    public String saveMovie(String movie) {
        return webClientMovie.method(HttpMethod.POST).uri("/addMovie").body(Mono.just(movie), String.class)
                .retrieve().bodyToMono(String.class).block();
    }

    @Override
    public String updateMovie(int id, String movie) {
        return webClientMovie.method(HttpMethod.PUT).uri("/update/{id}", id).body(Mono.just(movie), String.class)
                .retrieve().bodyToMono(String.class).block();
    }

    @Override
    public void deleteMovieByID(int id) {
        webClientMovie.method(HttpMethod.DELETE).uri("/delete/{id}", id).retrieve().bodyToMono(String.class);

    }

    @Override
    public void deleteCommentByID(int id) {
        webClientMovie.method(HttpMethod.DELETE).uri("/delete/{id}", id).retrieve().bodyToMono(String.class);

    }

    @Override
    public void deleteWatchlistByID(int id) {
        watchListRepository.deleteById(id);
    }

    @Override
    public String addUser(User user) {

        return userRepository.save(user).toString();
    }

    @Override
    public void deleteUserByID(int id) {
        userRepository.deleteById(id);

    }

    @Override
    public String generateToken() {
        return webClientAuth.method(HttpMethod.GET).uri("/token/generate").retrieve().bodyToMono(String.class).block();
    }

    @Override
    public String validateToken(String token) {
        return webClientAuth.method(HttpMethod.GET).uri("/token/validate").retrieve().bodyToMono(String.class).block();
    }

    @Override
    public void invalidateToken(String user) {
        webClientAuth.method(HttpMethod.POST).uri("/token/invalidate").retrieve();
    }
}
