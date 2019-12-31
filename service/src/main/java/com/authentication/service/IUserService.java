package com.authentication.service;

import java.util.List;
import java.util.Optional;

import com.authentication.model.User;
import com.authentication.service.dto.UserDto;

public interface IUserService {

    List<UserDto> findAll();

    Optional<UserDto> findDtoById(Long userId);

    Optional<User> findById(Long userId);

    UserDto save(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void delete(User user);

}
