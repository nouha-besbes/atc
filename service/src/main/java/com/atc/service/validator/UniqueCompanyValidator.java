package com.atc.service.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.atc.repository.ICompanyRepository;
import com.atc.service.annotation.UniqueCompany;

public class UniqueCompanyValidator implements ConstraintValidator<UniqueCompany, String> {

    @Autowired
    private ICompanyRepository companyRepository;

    @Override
    public boolean isValid(String companyName, ConstraintValidatorContext context) {
        return !companyRepository.findByName(companyName).isPresent();
    }

}
