package com.example.microservices.watchlistservice.security;

import com.example.microservices.watchlistservice.entity.User;
import com.example.microservices.watchlistservice.repositories.UserRepository;
import com.example.microservices.watchlistservice.service.WatchListService;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private WatchListService watchListService;

    public CustomUserDetailsService() {
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        System.out.println("username inloadUserByUsername:" + username);
        System.out.println(watchListService.findUserByUserName(username).getId() + " usernamefind");
        User user = watchListService.findUserByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserPrincipal(user);
    }
}
