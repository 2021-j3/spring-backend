package com.ecommerce.j3.domain.entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// https://www.baeldung.com/spring-mvc-custom-validator
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 전화번호 규칙 https://regex101.com/r/HRgn9l/1
        return value.equals("") || value.matches("01[01](-\\d{3,4}){2}")
                && (value.length() > 8) && (value.length() < 14);
    }
}
