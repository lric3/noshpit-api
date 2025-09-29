package com.lric3.noshpit.api.validate;

import com.lric3.noshpit.api.dto.UserRegistrationDto;
import com.lric3.noshpit.api.entity.User;
import com.lric3.noshpit.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final String userNotFoundMsg = "User not found with identifier: ";

    private final UserRepository userRepository;

    public void validateRegistration(UserRegistrationDto registrationDto) {

        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and confirmation password do not match");
        }

        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    public User validateUser(Optional<User> user, Long id) {
        if (user.isEmpty()) {
            throw new IllegalArgumentException(userNotFoundMsg + id);
        }
        return user.get();
    }

    public User validateUser(Optional<User> user, String identifier) {
        if (user.isEmpty()) {
            throw new IllegalArgumentException(userNotFoundMsg + identifier);
        }
        return user.get();
    }

    public void validateUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException(userNotFoundMsg + id);
        }
    }
}
