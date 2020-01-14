package com.authentication.service;

import java.util.List;

import javax.validation.Valid;

import com.authentication.service.dto.CompanyDto;
import com.authentication.service.exception.ResourceNotFoundException;

public interface ICompanyService {

    CompanyDto save(@Valid CompanyDto companyDto);

    CompanyDto updateCompany(Long companyId, @Valid CompanyDto companyDetails) throws ResourceNotFoundException;

    CompanyDto findDtoById(Long companyId) throws ResourceNotFoundException;

    void deleteById(Long companyId) throws ResourceNotFoundException;

    List<CompanyDto> findAll();

}
