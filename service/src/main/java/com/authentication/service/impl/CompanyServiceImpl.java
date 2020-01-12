package com.authentication.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication.model.Company;
import com.authentication.repository.ICompanyRepository;
import com.authentication.service.ICompanyService;
import com.authentication.service.dto.CompanyDto;
import com.authentication.service.exception.ResourceNotFoundException;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private ICompanyRepository companyRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CompanyDto save(@Valid CompanyDto companyDto) {
        return modelMapper.map(companyRepository.save(modelMapper.map(companyDto, Company.class)), CompanyDto.class);
    }

    @Override
    public CompanyDto updateCompany(Long companyId, @Valid CompanyDto companyDetails) throws ResourceNotFoundException {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + companyId));
        company = modelMapper.map(companyDetails, Company.class);
        company.setId(companyId);
        companyRepository.save(company);
        companyDetails.setId(companyId);
        return companyDetails;
    }

    @Override
    public CompanyDto findDtoById(Long companyId) {
        return modelMapper.map(companyRepository.findById(companyId), CompanyDto.class);
    }

    @Override
    public void deleteById(Long companyId) throws ResourceNotFoundException {
        Optional.of(companyRepository.existsById(companyId))
                .orElseThrow(() -> new ResourceNotFoundException("Company not found on :: " + companyId));
        companyRepository.deleteById(companyId);
    }

    @Override
    public List<CompanyDto> findAll() {
        List<Company> Companys = companyRepository.findAll();
        return Companys.stream().map(company -> modelMapper.map(company, CompanyDto.class))
                .collect(Collectors.toList());
    }

}
