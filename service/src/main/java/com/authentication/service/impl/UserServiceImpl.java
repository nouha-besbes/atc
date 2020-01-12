package com.authentication.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication.model.User;
import com.authentication.repository.IUserRepository;
import com.authentication.service.IUserService;
import com.authentication.service.dto.UserDto;
import com.authentication.service.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findDtoById(Long userId) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User not found" + userId);
        }
        return Optional.of(modelMapper.map(user.get(), UserDto.class));
    }

    @Override
    public UserDto save(UserDto userDto) {
        return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
        user = modelMapper.map(userDto, User.class);
        user.setId(userId);
        userRepository.save(user);
        userDto.setId(userId);
        return userDto;
    }

    @Override
    public void deleteById(Long userId) throws ResourceNotFoundException {
        Optional.of(userRepository.existsById(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        userRepository.deleteById(userId);

    }

}
