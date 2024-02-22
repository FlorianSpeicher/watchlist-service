package com.example.microservices.watchlistservice.utils.watchlist;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WatchListNameConstraintValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@SuppressWarnings("unused")
public @interface ValidWatchlistName {
    String message() default "Invalid Watchlist name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
