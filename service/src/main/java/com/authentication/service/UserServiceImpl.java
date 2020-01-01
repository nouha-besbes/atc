package com.authentication.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication.model.User;
import com.authentication.repository.IUserRepository;
import com.authentication.service.dto.UserDto;

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
    public Optional<UserDto> findDtoById(Long userId) {
        return Optional.of(modelMapper.map(userRepository.findById(userId).get(), UserDto.class));
    }

    @Override
    public UserDto save(UserDto userDto) {
        return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        Optional<User> optionalUser = this.findById(userId);
        // .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        optionalUser.get().setEmail(userDto.getEmail());
        optionalUser.get().setLastName(userDto.getLastName());
        optionalUser.get().setFirstName(userDto.getFirstName());

        userRepository.save(optionalUser.get());
        userDto.setId(userId);
        return userDto;
    }

}
