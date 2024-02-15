package com.example.microservices.watchlistservice.utils.watchlist;

import com.example.microservices.watchlistservice.utils.username.UserNameConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserNameConstraintValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidWatchlistName {
    String message() default "Invalid Watchlistname";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
