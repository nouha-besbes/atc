package com.atc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atc.model.Company;
import com.atc.repository.IAffiliateRepository;
import com.atc.repository.ICompanyRepository;
import com.atc.service.ICompanyService;
import com.atc.service.dto.CompanyDto;
import com.atc.service.exception.MethodNotAllowedException;
import com.atc.service.exception.ResourceNotFoundException;

@Service
public class CompanyServiceImpl implements ICompanyService {

    private final ICompanyRepository companyRepository;

    private final IAffiliateRepository affiliateRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public CompanyServiceImpl(ICompanyRepository companyRepository, IAffiliateRepository affiliateRepository) {
        super();
        this.companyRepository = companyRepository;
        this.affiliateRepository = affiliateRepository;
    }

    @Override
    public CompanyDto save(@Valid CompanyDto companyDto) {
        return modelMapper.map(companyRepository.save(modelMapper.map(companyDto, Company.class)), CompanyDto.class);
    }

    @Override
    public CompanyDto update(Long companyId, @Valid CompanyDto companyDetails) throws ResourceNotFoundException {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found on :: " + companyId));
        company.setName(companyDetails.getName());
        company.setId(companyId);
        companyRepository.save(company);
        companyDetails.setId(companyId);
        return companyDetails;
    }

    @Override
    public CompanyDto findDtoById(Long companyId) throws ResourceNotFoundException {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found on :: " + companyId));
        return modelMapper.map(company, CompanyDto.class);
    }

    @Override
    public void deleteById(Long companyId) throws ResourceNotFoundException, MethodNotAllowedException {
        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Company not found on :: " + companyId);
        }
        if (affiliateRepository.existsByCompanyId(companyId)) {
            throw new MethodNotAllowedException("Affiliate associated on :: " + companyId);
        }
        companyRepository.deleteById(companyId);
    }

    @Override
    public List<CompanyDto> findAll() {
        List<Company> companys = companyRepository.findAll();
        return companys.stream().map(company -> modelMapper.map(company, CompanyDto.class))
                .collect(Collectors.toList());
    }

}
