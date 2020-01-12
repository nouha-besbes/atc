package com.authentication.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication.model.Affiliate;
import com.authentication.model.Company;
import com.authentication.repository.IAffiliateRepository;
import com.authentication.repository.ICompanyRepository;
import com.authentication.service.IAffiliateService;
import com.authentication.service.dto.AffiliateDto;
import com.authentication.service.exception.ResourceNotFoundException;

@Service
public class AffiliateServiceImpl implements IAffiliateService {

    @Autowired
    private IAffiliateRepository affiliateRepository;

    @Autowired
    private ICompanyRepository companyRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public AffiliateDto save(@Valid AffiliateDto affiliateDto) throws ResourceNotFoundException {
        Affiliate affiliate = modelMapper.map(affiliateDto, Affiliate.class);
        Company company = companyRepository.findById(affiliateDto.getCompanyDto().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Company not found on :: " + affiliateDto.getCompanyDto().getId()));
        affiliate.setCompany(company);
        return modelMapper.map(affiliateRepository.save(affiliate), AffiliateDto.class);
    }

    @Override
    public AffiliateDto updateAffiliate(Long affiliateId, @Valid AffiliateDto affiliateDetails)
            throws ResourceNotFoundException {
        Affiliate affiliate = affiliateRepository.findById(affiliateId)
                .orElseThrow(() -> new ResourceNotFoundException("Affiliate not found on :: " + affiliateId));
        Company company = companyRepository.findById(affiliateDetails.getCompanyDto().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Company not found on :: " + affiliateDetails.getCompanyDto().getId()));

        affiliate.setName(affiliateDetails.getName());
        affiliate.setId(affiliateId);
        affiliate.setCompany(company);
        affiliateRepository.save(affiliate);
        affiliateDetails.setId(affiliateId);
        return affiliateDetails;
    }

    @Override
    public AffiliateDto findDtoById(Long affiliateId) {
        return modelMapper.map(affiliateRepository.findById(affiliateId), AffiliateDto.class);
    }

    @Override
    public void deleteById(Long affiliateId) throws ResourceNotFoundException {
        Optional.of(affiliateRepository.existsById(affiliateId))
                .orElseThrow(() -> new ResourceNotFoundException("Company not found on :: " + affiliateId));
        affiliateRepository.deleteById(affiliateId);
    }

    @Override
    public List<AffiliateDto> findAll() {
        List<Affiliate> affiliates = affiliateRepository.findAll();
        return affiliates.stream().map(company -> modelMapper.map(company, AffiliateDto.class))
                .collect(Collectors.toList());
    }

}
