package com.example.microservices.watchlistservice.entity;

import com.example.microservices.watchlistservice.repositories.WatchListRepository;
import com.example.microservices.watchlistservice.utils.password.ValidPassword;
import com.example.microservices.watchlistservice.utils.username.ValidUserName;
import com.example.microservices.watchlistservice.utils.watchlist.ValidWatchlistName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last name can not be empty")
    private String lastName;

    @Column(name = "user_name")
    @NotBlank(message = "User name can not be empty")
    @ValidUserName(message = "This user name is already in use")
    private String userName;

    @Column(name = "password")
    @NotBlank(message = "Password can not be empty")
    @ValidPassword(message = "The password must be in an other format")
    private String password;

    @Column(name = "age")
    @Range(min = 0, max = 150, message = "Age must be between 0 and 150")
    private int age;

    @Column(name = "email")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Incorrect email type")
    private String email;

    @Column(name = "token")
    private String token;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private Role roles;

    @Column(name = "active")
    private int active;

    public User(User user) {
    }


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

    public User(String firstName, String lastName, String userName, String password, int age, String email, String token, Role roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.email = email;
        this.token = token;
        this.roles = roles;
    }

    public User(){
        this("", "", "", "", 0, "", null , new Role());
    }

}
