package com.example.microservices.watchlistservice.utils.username;

import com.example.microservices.watchlistservice.repositories.UserRepository;
import com.example.microservices.watchlistservice.service.EntityService;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;

@Component
public class UserNameConstraintValidator implements ConstraintValidator<ValidUserName, String> {

    @Autowired
    private EntityService entityService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(ValidUserName arg0) {
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        boolean list = userRepository.existsByuserName(userName);
        System.out.println(list);
        if (userRepository == null){
            return true;
        }
        System.out.println("Nach if");
        return userName != null && !userRepository.existsByuserName(userName);
    }
}
