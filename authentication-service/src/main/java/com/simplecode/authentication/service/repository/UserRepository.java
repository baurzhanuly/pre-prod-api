package com.simplecode.authentication.service.repository;

import com.simplecode.authentication.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByDeletedAtIsNull();
    Optional<User> findByUsernameAndDeletedAtIsNull(String username);
}
