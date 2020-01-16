package com.authentication.repository;

import com.authentication.model.User;

public interface IUserRepository extends IBaseRepository<User, Long> {

    boolean existsByAffiliateId(Long affiliateId);

}
