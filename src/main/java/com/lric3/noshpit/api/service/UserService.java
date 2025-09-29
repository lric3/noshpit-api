package com.lric3.noshpit.api.service;

import com.lric3.noshpit.api.dto.UserDto;
import com.lric3.noshpit.api.dto.UserRegistrationDto;
import com.lric3.noshpit.api.entity.User;
import com.lric3.noshpit.api.factory.UserFactory;
import com.lric3.noshpit.api.repository.UserRepository;
import com.lric3.noshpit.api.validate.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final UserFactory userFactory;

    public UserDto registerUser(UserRegistrationDto registrationDto) {
        userValidator.validateRegistration(registrationDto);
        User newUser = userFactory.createNewUser(registrationDto);
        User savedUser = userRepository.save(newUser);
        return userFactory.createUserDto(savedUser);
    }

    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        User validatedUser = userValidator.validateUser(user, id);
        return userFactory.createUserDto(validatedUser);
    }

    public UserDto getUserByEmailOrUsername(String identifier) {
        Optional<User> user = userRepository.findByEmailOrUsername(identifier, identifier);
        User validatedUser = userValidator.validateUser(user, identifier);
        return userFactory.createUserDto(validatedUser);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        Optional<User> userOpt = userRepository.findById(id);
        User user = userValidator.validateUser(userOpt, id);
        User savedUser = userRepository.save(user);
        return userFactory.createUserDto(savedUser);
    }

    public void deleteUser(Long id) {
        userValidator.validateUser(id);
        userRepository.deleteById(id);
    }
}
