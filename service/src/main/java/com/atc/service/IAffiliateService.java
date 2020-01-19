package com.atc.service;

import java.util.List;

import javax.validation.Valid;

import com.atc.service.dto.AffiliateDto;
import com.atc.service.exception.MethodNotAllowedException;
import com.atc.service.exception.ResourceNotFoundException;

public interface IAffiliateService {

    AffiliateDto save(@Valid AffiliateDto affiliateDto) throws ResourceNotFoundException;

    AffiliateDto update(Long affiliateId, @Valid AffiliateDto affiliateDto) throws ResourceNotFoundException;

    AffiliateDto findDtoById(Long affiliateId) throws ResourceNotFoundException;

    void deleteById(Long affiliateId) throws ResourceNotFoundException, MethodNotAllowedException;

    List<AffiliateDto> findAll();

}
