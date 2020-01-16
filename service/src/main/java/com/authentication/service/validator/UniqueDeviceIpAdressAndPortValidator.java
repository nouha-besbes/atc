package com.authentication.service.validator;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.authentication.repository.IDeviceRepository;
import com.authentication.service.annotation.UniqueDeviceIpAdressAndPort;

public class UniqueDeviceIpAdressAndPortValidator implements ConstraintValidator<UniqueDeviceIpAdressAndPort, Object> {

    private String ipAdressField;
    private String portField;

    @Override
    public void initialize(UniqueDeviceIpAdressAndPort constraintAnnotation) {
        ipAdressField = constraintAnnotation.ipAdressField();
        portField = constraintAnnotation.portField();
    }

    @Autowired
    private IDeviceRepository deviceRepository;

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {

        try {
            Object ipAdressFieldValue = getFieldValue(object, ipAdressField);
            Object portFieldValue = getFieldValue(object, portField);
            return !deviceRepository
                    .findByIpAdressAndPort(ipAdressFieldValue.toString(), Integer.valueOf(portFieldValue.toString()))
                    .isPresent();
        } catch (Exception e) {
            // log error
            return false;
        }

    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field passwordField = clazz.getDeclaredField(fieldName);
        passwordField.setAccessible(true);
        return passwordField.get(object);
    }

}
