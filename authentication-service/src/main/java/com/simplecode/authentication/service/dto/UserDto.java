package com.simplecode.authentication.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
}
