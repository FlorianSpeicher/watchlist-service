package com.example.microservices.watchlistservice.utils.username;

import com.example.microservices.watchlistservice.service.EntityService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.parser.Entity;

public class UserNameConstraintValidator implements ConstraintValidator<ValidUserName, String> {

    @Autowired
    private EntityService entityService;
    @Override
    public void initialize(ValidUserName arg0) {
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        return userName != null && !entityService.isUserNameInUse(userName);
    }
}
