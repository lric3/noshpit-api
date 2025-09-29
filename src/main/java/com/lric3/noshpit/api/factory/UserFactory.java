package com.lric3.noshpit.api.factory;

import com.lric3.noshpit.api.dto.UserDto;
import com.lric3.noshpit.api.dto.UserRegistrationDto;
import com.lric3.noshpit.api.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    private final PasswordEncoder passwordEncoder;

    public UserFactory(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User createNewUser(UserRegistrationDto registrationDto) {

        User newUser = new User();
        newUser.setEmail(registrationDto.getEmail());
        newUser.setFirstName(registrationDto.getFirstName());
        newUser.setLastName(registrationDto.getLastName());
        newUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        newUser.setUsername(registrationDto.getUsername());
        return newUser;
    }

    public UserDto createUserDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .build();
    }
}
