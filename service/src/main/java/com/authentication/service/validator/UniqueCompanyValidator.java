package com.authentication.service.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.authentication.repository.ICompanyRepository;
import com.authentication.service.annotation.UniqueCompany;

public class UniqueCompanyValidator implements ConstraintValidator<UniqueCompany, String> {

    @Autowired
    private ICompanyRepository companyRepository;

    @Override
    public boolean isValid(String companyName, ConstraintValidatorContext context) {
        return !companyRepository.findByName(companyName).isPresent();
    }

}
