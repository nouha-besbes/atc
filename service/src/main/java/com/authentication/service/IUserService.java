package com.authentication.service;

import java.io.Serializable;
import java.util.List;

import com.authentication.model.User;

public interface IUserService extends IGenericService<User, Serializable> {

    List<User> findAll();
}
