package com.authentication.service;

import java.util.List;
import java.util.Optional;

import com.authentication.service.dto.UserDto;
import com.authentication.service.exception.ResourceNotFoundException;

public interface IUserService {

    List<UserDto> findAll();

    Optional<UserDto> findDtoById(Long userId) throws ResourceNotFoundException;

    UserDto save(UserDto userDto);

    UserDto updateUser(Long userId, UserDto userDto) throws ResourceNotFoundException;

    void deleteById(Long userId) throws ResourceNotFoundException;

}
