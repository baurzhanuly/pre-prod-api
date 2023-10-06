package com.simplecode.authentication.service.service;

import com.simplecode.authentication.service.dto.CreateRoleDto;
import com.simplecode.authentication.service.dto.RoleDto;
import com.simplecode.authentication.service.exception.ApiException;
import com.simplecode.authentication.service.exception.ErrorCode;
import com.simplecode.authentication.service.mapper.RoleMapper;
import com.simplecode.authentication.service.model.Role;
import com.simplecode.authentication.service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAllByDeletedAtIsNull()
                .stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto add(CreateRoleDto createRoleDto) {
        Optional<Role> roleOptional = roleRepository.findByName(createRoleDto.getName());

        if (roleOptional.isPresent()) {
            throw new ApiException(ErrorCode.ALREADY_EXISTS, "role already exists");
        }
        var entity = toEntity(createRoleDto);
        return roleMapper.toDto(roleRepository.save(entity));
    }

    private Role toEntity(CreateRoleDto dto) {
        var role = new Role();
        role.setName(dto.getName());
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
        return role;
    }
}
