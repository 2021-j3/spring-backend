package com.ecommerce.j3.domain.mapper;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
// https://www.baeldung.com/spring-mvc-custom-validator
@Documented
@Constraint(validatedBy= PhoneNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
