package com.simplecode.authentication.service.service;

import com.simplecode.authentication.service.dto.CreateRoleDto;
import com.simplecode.authentication.service.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();
    Role createRole(CreateRoleDto request);
}
