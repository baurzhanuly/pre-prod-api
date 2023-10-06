package com.simplecode.authentication.service.dto;

import lombok.Data;

@Data
public class CreateUserDto {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
}
