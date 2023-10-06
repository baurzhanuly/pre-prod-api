package com.simplecode.authentication.service.controller;

import com.simplecode.authentication.service.dto.CreateUserDto;
import com.simplecode.authentication.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> add(@RequestBody CreateUserDto createUserDto) {
        return new ResponseEntity<>(userService.add(createUserDto), HttpStatus.OK);
    }
}
