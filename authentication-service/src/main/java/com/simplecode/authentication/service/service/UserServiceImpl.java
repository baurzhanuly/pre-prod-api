package com.simplecode.authentication.service.service;

import com.simplecode.authentication.service.jwt.JwtUserDetails;
import com.simplecode.authentication.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameAndDeletedAtIsNull(username)
                .map(JwtUserDetails::build)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
    }
}
