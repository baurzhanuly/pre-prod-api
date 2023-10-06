package com.simplecode.authentication.service.service;

import com.simplecode.authentication.service.dto.CreateUserDto;
import com.simplecode.authentication.service.dto.UserDto;
import com.simplecode.authentication.service.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDto> findAll();
    UserDto add(CreateUserDto createUserDto);
}
