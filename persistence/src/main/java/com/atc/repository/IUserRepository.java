package com.atc.repository;

import com.atc.model.User;

public interface IUserRepository extends IBaseRepository<User, Long> {

    boolean existsByAffiliateId(Long affiliateId);

}
