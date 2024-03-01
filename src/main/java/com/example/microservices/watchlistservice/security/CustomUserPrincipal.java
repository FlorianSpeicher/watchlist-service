package com.example.microservices.watchlistservice.security;

import com.example.microservices.watchlistservice.entity.Role;
import com.example.microservices.watchlistservice.entity.Roles;
import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.service.WatchListService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

public class CustomUserPrincipal implements UserDetails {

    @Autowired
    private WatchListService watchListService;
    private User user;
    private  CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();
    public CustomUserPrincipal(User user){
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        if (user.getRoles().getRole().equals("ROLE_ADMIN")){
            list.add(new SimpleGrantedAuthority(Roles.ROLE_USER.name()));
            list.add(new SimpleGrantedAuthority(Roles.ROLE_ADMIN.name()));
        } else {
            list.add(new SimpleGrantedAuthority(Roles.ROLE_USER.name()));
        }

        return list;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
