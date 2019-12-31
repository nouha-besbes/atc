package com.authentication.service;

import java.util.List;
import java.util.Optional;

import com.authentication.model.User;

public interface IUserService {

    List<User> findAll();

    Optional<User> findById(Long userId);

    User save(User user);

    void delete(User user);

}
