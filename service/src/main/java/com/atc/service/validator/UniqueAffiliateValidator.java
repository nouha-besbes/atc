package com.atc.service.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.atc.repository.IAffiliateRepository;
import com.atc.service.annotation.UniqueAffiliate;

public class UniqueAffiliateValidator implements ConstraintValidator<UniqueAffiliate, String> {

    @Autowired
    private IAffiliateRepository affiliateRepository;

    @Override
    public boolean isValid(String affiliateName, ConstraintValidatorContext context) {
        return !affiliateRepository.findByName(affiliateName).isPresent();
    }

}
