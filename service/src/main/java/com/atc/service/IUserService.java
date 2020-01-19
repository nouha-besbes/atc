package com.atc.service;

import java.util.List;
import java.util.Optional;

import com.atc.service.dto.UserDto;
import com.atc.service.exception.MethodNotAllowedException;
import com.atc.service.exception.ResourceNotFoundException;

public interface IUserService {

    List<UserDto> findAll();

    Optional<UserDto> findDtoById(Long userId) throws ResourceNotFoundException;

    UserDto save(UserDto userDto);

    UserDto update(Long userId, UserDto userDto) throws ResourceNotFoundException;

    void deleteById(Long userId) throws ResourceNotFoundException, MethodNotAllowedException;

}
