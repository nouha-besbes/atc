package com.authentication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication.model.User;
import com.authentication.repository.IUserRepository;

@Service
public class UserServiceImpl extends GenericService<User> implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

}
