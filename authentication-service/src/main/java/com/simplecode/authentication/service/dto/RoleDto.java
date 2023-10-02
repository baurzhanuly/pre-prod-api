package com.simplecode.authentication.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleDto {

    private Integer id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
