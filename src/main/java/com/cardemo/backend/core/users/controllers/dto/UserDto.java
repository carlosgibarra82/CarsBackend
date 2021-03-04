package com.cardemo.backend.core.users.controllers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto {
    private long Id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String password;
}
