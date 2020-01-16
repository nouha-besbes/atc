package com.authentication.repository;

import java.util.Optional;

import com.authentication.model.Affiliate;

public interface IAffiliateRepository extends IBaseRepository<Affiliate, Long> {

    Optional<Affiliate> findByName(String affiliateName);

    boolean existsByCompanyId(Long companyId);

}
