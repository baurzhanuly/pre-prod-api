package com.simplecode.authentication.service.mapper;

import com.simplecode.authentication.service.dto.UserDto;
import com.simplecode.authentication.service.model.User;
import com.simplecode.authentication.service.utils.AbstractModelMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper extends AbstractModelMapper<User, UserDto> {

    private final ModelMapper modelMapper;

    @Override
    public UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User toEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public List<UserDto> toDtoList(List<User> users) {
        return null;
    }

    @Override
    public List<User> toEntityList(List<UserDto> userDtos) {
        return null;
    }
}
