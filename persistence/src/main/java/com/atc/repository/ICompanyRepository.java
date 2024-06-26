package com.atc.repository;

import java.util.Optional;

import com.atc.model.Company;

public interface ICompanyRepository extends IBaseRepository<Company, Long> {

    Optional<Company> findByName(String companyName);

}
