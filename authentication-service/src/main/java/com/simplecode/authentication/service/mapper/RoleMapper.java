package com.simplecode.authentication.service.mapper;

import com.simplecode.authentication.service.dto.RoleDto;
import com.simplecode.authentication.service.model.Role;
import com.simplecode.authentication.service.utils.AbstractModelMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleMapper extends AbstractModelMapper<Role, RoleDto> {

    private final ModelMapper mapper;

    @Override
    public RoleDto toDto(Role role) {
        return mapper.map(role, RoleDto.class);
    }

    @Override
    public Role toEntity(RoleDto roleDto) {
        return mapper.map(roleDto, Role.class);
    }

    @Override
    public List<RoleDto> toDtoList(List<Role> roles) {
        return roles.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Role> toEntityList(List<RoleDto> roleDtoList) {
        return roleDtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
