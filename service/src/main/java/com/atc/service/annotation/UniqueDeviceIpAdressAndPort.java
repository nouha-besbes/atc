package com.atc.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.atc.service.validator.UniqueDeviceIpAdressAndPortValidator;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDeviceIpAdressAndPortValidator.class)
public @interface UniqueDeviceIpAdressAndPort {
    String ipAdressField();

    String portField();

    String message() default "The given ip adress and port is already in use";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
