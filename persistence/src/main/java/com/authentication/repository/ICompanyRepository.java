package com.authentication.repository;

import java.util.Optional;

import com.authentication.model.Company;

public interface ICompanyRepository extends IBaseRepository<Company, Long> {

    Optional<Company> findByName(String companyName);

}
