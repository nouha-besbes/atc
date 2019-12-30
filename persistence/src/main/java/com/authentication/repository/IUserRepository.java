package com.authentication.repository;

import org.springframework.stereotype.Repository;

import com.authentication.model.User;

@Repository
public interface IUserRepository extends IBaseRepository<User, Long> {

}
