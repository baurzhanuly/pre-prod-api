package com.simplecode.authentication.service.service;

import com.simplecode.authentication.service.dto.CreateRoleDto;
import com.simplecode.authentication.service.exception.ApiException;
import com.simplecode.authentication.service.exception.ErrorCode;
import com.simplecode.authentication.service.model.Role;
import com.simplecode.authentication.service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public Role createRole(CreateRoleDto createRoleDto) {
        Optional<Role> roleOptional = roleRepository.findByName(createRoleDto.getName());

        if (roleOptional.isPresent()) {
            throw new ApiException(ErrorCode.ALREADY_EXISTS, "role already exists");
        }
        var entity = toEntity(createRoleDto);
        return roleRepository.save(entity);
    }

    private Role toEntity(CreateRoleDto dto) {
        var role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
        return role;
    }
}
