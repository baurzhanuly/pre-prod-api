package com.simplecode.authentication.service.service;

import com.simplecode.authentication.service.dto.CreateRoleDto;
import com.simplecode.authentication.service.dto.RoleDto;
import com.simplecode.authentication.service.model.Role;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAll();
    RoleDto add(CreateRoleDto createRoleDto);
}
