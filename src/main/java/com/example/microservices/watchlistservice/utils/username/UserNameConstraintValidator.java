package com.example.microservices.watchlistservice.utils.username;

import com.example.microservices.watchlistservice.repositories.UserRepository;
import com.example.microservices.watchlistservice.service.EntityService;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;

@Component
@Configurable
public class UserNameConstraintValidator implements ConstraintValidator<ValidUserName, String> {



    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(ValidUserName arg0) {

    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        try {
            if (userRepository.findByUserName(userName) == null){
                return true;
            } else {
                return false;
            }
        }catch (NullPointerException e){
            return true;
        }
    }
}
