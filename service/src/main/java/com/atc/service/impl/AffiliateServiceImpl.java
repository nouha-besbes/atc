package com.atc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atc.model.Affiliate;
import com.atc.model.Company;
import com.atc.repository.IAffiliateRepository;
import com.atc.repository.ICompanyRepository;
import com.atc.repository.IDeviceRepository;
import com.atc.repository.IUserRepository;
import com.atc.service.IAffiliateService;
import com.atc.service.dto.AffiliateDto;
import com.atc.service.exception.MethodNotAllowedException;
import com.atc.service.exception.ResourceNotFoundException;

@Service
public class AffiliateServiceImpl implements IAffiliateService {

    private final IAffiliateRepository affiliateRepository;

    private final ICompanyRepository companyRepository;

    private final IDeviceRepository deviceRepository;

    private final IUserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public AffiliateServiceImpl(IAffiliateRepository affiliateRepository, ICompanyRepository companyRepository,
            IDeviceRepository deviceRepository, IUserRepository userRepository) {
        super();
        this.affiliateRepository = affiliateRepository;
        this.companyRepository = companyRepository;
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AffiliateDto save(@Valid AffiliateDto affiliateDto) throws ResourceNotFoundException {
        Affiliate affiliate = modelMapper.map(affiliateDto, Affiliate.class);
        Company company = companyRepository.findById(affiliateDto.getCompany().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Company not found on :: " + affiliateDto.getCompany().getId()));
        affiliate.setCompany(company);
        return modelMapper.map(affiliateRepository.save(affiliate), AffiliateDto.class);
    }

    @Override
    public AffiliateDto update(Long affiliateId, @Valid AffiliateDto affiliateDetails)
            throws ResourceNotFoundException {
        Affiliate affiliate = affiliateRepository.findById(affiliateId)
                .orElseThrow(() -> new ResourceNotFoundException("Affiliate not found on :: " + affiliateId));
        Company company = companyRepository.findById(affiliateDetails.getCompany().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Company not found on :: " + affiliateDetails.getCompany().getId()));

        affiliate.setName(affiliateDetails.getName());
        affiliate.setId(affiliateId);
        affiliate.setCompany(company);
        affiliateRepository.save(affiliate);
        affiliateDetails.setId(affiliateId);
        return affiliateDetails;
    }

    @Override
    public AffiliateDto findDtoById(Long affiliateId) throws ResourceNotFoundException {
        Affiliate affiliate = affiliateRepository.findById(affiliateId)
                .orElseThrow(() -> new ResourceNotFoundException("Affiliate not found on :: " + affiliateId));
        return modelMapper.map(affiliate, AffiliateDto.class);
    }

    @Override
    public void deleteById(Long affiliateId) throws ResourceNotFoundException, MethodNotAllowedException {
        if (!affiliateRepository.existsById(affiliateId)) {
            throw new ResourceNotFoundException("Affiliate not found on :: " + affiliateId);
        }
        if (deviceRepository.existsByAffiliateId(affiliateId) || userRepository.existsByAffiliateId(affiliateId)) {
            throw new MethodNotAllowedException("Device or user associated on :: " + affiliateId);
        }
        affiliateRepository.deleteById(affiliateId);
    }

    @Override
    public List<AffiliateDto> findAll() {
        List<Affiliate> affiliates = affiliateRepository.findAll();
        return affiliates.stream().map(affiliate -> modelMapper.map(affiliate, AffiliateDto.class))
                .collect(Collectors.toList());
    }

}
