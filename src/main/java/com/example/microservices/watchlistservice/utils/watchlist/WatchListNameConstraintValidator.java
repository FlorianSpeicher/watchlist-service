package com.example.microservices.watchlistservice.utils.watchlist;

import com.example.microservices.watchlistservice.service.EntityService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WatchListNameConstraintValidator implements ConstraintValidator<ValidWatchlistName, String> {

    private EntityService entityService;
    @Override
    public void initialize(ValidWatchlistName arg0) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return name != null && !entityService.isWatchListNameInUse(name);
    }
}
