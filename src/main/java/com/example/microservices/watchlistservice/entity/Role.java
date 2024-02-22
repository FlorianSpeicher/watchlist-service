package com.example.microservices.watchlistservice.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
@SuppressWarnings("unused")
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role")
    private Roles role;

    @OneToMany(mappedBy = "roles")
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Role() {
        this.role = Roles.USER;
    }
}
