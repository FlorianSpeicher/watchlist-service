package com.example.microservices.watchlistservice.utils.username;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserNameConstraintValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@SuppressWarnings("unused")
public @interface ValidUserName {
    String message() default "Invalid Username";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
