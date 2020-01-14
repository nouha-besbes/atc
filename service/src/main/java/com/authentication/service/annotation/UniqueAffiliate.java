package com.authentication.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.authentication.service.validator.UniqueAffiliateValidator;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueAffiliateValidator.class)
public @interface UniqueAffiliate {
    String message() default "The given affiliate name is already in use";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
