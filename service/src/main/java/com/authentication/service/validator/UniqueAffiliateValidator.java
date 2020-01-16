package com.authentication.service.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.authentication.repository.IAffiliateRepository;
import com.authentication.service.annotation.UniqueAffiliate;

public class UniqueAffiliateValidator implements ConstraintValidator<UniqueAffiliate, String> {

    @Autowired
    private IAffiliateRepository affiliateRepository;

    @Override
    public boolean isValid(String affiliateName, ConstraintValidatorContext context) {
        return !affiliateRepository.findByName(affiliateName).isPresent();
    }

}
