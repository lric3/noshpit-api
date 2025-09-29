package com.lric3.noshpit.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
