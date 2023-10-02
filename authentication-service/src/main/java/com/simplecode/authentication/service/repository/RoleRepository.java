package com.simplecode.authentication.service.repository;

import com.simplecode.authentication.service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findAllByDeletedAtIsNull();
    Optional<Role> findByName(String name);
}
