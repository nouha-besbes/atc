package com.authentication.service;

import java.util.List;

import javax.validation.Valid;

import com.authentication.service.dto.AffiliateDto;
import com.authentication.service.exception.MethodNotAllowedException;
import com.authentication.service.exception.ResourceNotFoundException;

public interface IAffiliateService {

    AffiliateDto save(@Valid AffiliateDto affiliateDto) throws ResourceNotFoundException;

    AffiliateDto update(Long affiliateId, @Valid AffiliateDto affiliateDto) throws ResourceNotFoundException;

    AffiliateDto findDtoById(Long affiliateId) throws ResourceNotFoundException;

    void deleteById(Long affiliateId) throws ResourceNotFoundException, MethodNotAllowedException;

    List<AffiliateDto> findAll();

}
