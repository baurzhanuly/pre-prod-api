package com.simplecode.authentication.service.service;

import com.simplecode.authentication.service.dto.CreateUserDto;
import com.simplecode.authentication.service.dto.UserDto;
import com.simplecode.authentication.service.exception.ApiException;
import com.simplecode.authentication.service.exception.ErrorCode;
import com.simplecode.authentication.service.jwt.JwtUserDetails;
import com.simplecode.authentication.service.mapper.UserMapper;
import com.simplecode.authentication.service.model.User;
import com.simplecode.authentication.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameAndDeletedAtIsNull(username)
                .map(JwtUserDetails::build)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
    }


    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(
                user -> UserDto.builder()
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public UserDto add(CreateUserDto createUserDto) {
        Optional<User> user = userRepository.findByUsernameAndDeletedAtIsNull(createUserDto.getUsername());

        if (user.isPresent()) {
            throw new ApiException(ErrorCode.ALREADY_EXISTS, "user already exists");
        }

        var entity = toEntity(createUserDto);
        return userMapper.toDto(entity);
    }

    private User toEntity(CreateUserDto dto) {
        var user = new User();

        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return user;
    }
}
