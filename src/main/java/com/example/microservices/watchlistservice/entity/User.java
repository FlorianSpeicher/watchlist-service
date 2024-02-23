package com.example.microservices.watchlistservice.entity;

import com.example.microservices.watchlistservice.utils.password.ValidPassword;
import com.example.microservices.watchlistservice.utils.username.ValidUserName;
import com.example.microservices.watchlistservice.utils.watchlist.ValidWatchlistName;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@SuppressWarnings("unused")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "first_name")
    @NotEmpty(message = "Firstname cannot be empty")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Lastname cannot be empty")
    @NotNull
    private String lastName;

    @Column(name = "user_name")
    @NotEmpty(message = "Username cannot be empty")
    @NotNull
    @ValidUserName
    private String userName;

    @Column(name = "password")
    @NotEmpty(message = "Username cannot be empty")
    @NotNull
    @ValidPassword
    private String password;

    @Column(name = "age")
    @NotEmpty(message = "Age cannot be empty")
    @NotNull
    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 150, message = "You cannot be older than 150")
    private int age;

    @Column(name = "email")
    @NotEmpty(message = "Email cannot be empty")
    @NotNull
    @Email
    private String email;

    @Column(name = "token")
    private String token;

    @OneToMany
    @JoinTable(name = "watchlists")
    private List<WatchList> watchLists;

    @ManyToOne
    @JoinTable(name = "roles")
    private Role roles;

    @Column(name = "active")
    private int active;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<WatchList> getWatchLists() {
        return watchLists;
    }

    public void setWatchLists(List<WatchList> watchLists) {
        this.watchLists = watchLists;
    }

    @ValidWatchlistName
    public void addWatchLists(WatchList watchList){this.watchLists.add(watchList);}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public void addRoles(Role role){
        this.roles = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public User(String firstName, String lastName, String userName, String password, int age, String email, String token, List<WatchList> watchLists, Role roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.email = email;
        this.token = token;
        this.watchLists = watchLists;
        this.roles = roles;
    }

    public User(){
        this("unknown", "unknown", "unknown", "0000", 0, "unknown@unknown.com", null , new ArrayList<>(), new Role());
    }
}
