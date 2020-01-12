package com.authentication.service;

import java.util.List;

import javax.validation.Valid;

import com.authentication.service.dto.AffiliateDto;
import com.authentication.service.exception.ResourceNotFoundException;

public interface IAffiliateService {

    AffiliateDto save(@Valid AffiliateDto affiliateDto) throws ResourceNotFoundException;

    AffiliateDto updateAffiliate(Long affiliateId, @Valid AffiliateDto affiliateDto) throws ResourceNotFoundException;

    AffiliateDto findDtoById(Long affiliateId);

    void deleteById(Long affiliateId) throws ResourceNotFoundException;

    List<AffiliateDto> findAll();

}
