package com.atc.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.atc.service.validator.UniqueCompanyValidator;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCompanyValidator.class)
public @interface UniqueCompany {
    String message() default "The given company name is already in use";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
