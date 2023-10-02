package com.simplecode.authentication.service.controller;

import com.simplecode.authentication.service.dto.RoleDto;
import com.simplecode.authentication.service.mapper.RoleMapper;
import com.simplecode.authentication.service.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(roleMapper.toDtoList(roleService.findAll()), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> add(@RequestBody RoleDto roleDto) {
        return new ResponseEntity<>(roleMapper.toDto(roleService.add(roleDto)), HttpStatus.OK);
    }
}
