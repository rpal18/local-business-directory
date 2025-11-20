package com.rohitPal.local._business_directory.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MxEmailValidator.class)
@Documented
public @interface ValidMxEmail {
    /*
    Below methods are must have in order to create an annotation.
     */
    String message() default "invalid email , enter a valid Email .";
    Class<?> [] groups () default {};

    Class<? extends Payload> [] payload () default {} ;
}
