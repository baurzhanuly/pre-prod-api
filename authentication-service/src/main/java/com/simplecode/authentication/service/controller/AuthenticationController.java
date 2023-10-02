package com.simplecode.authentication.service.controller;

import com.simplecode.authentication.service.dto.AuthenticationRequest;
import com.simplecode.authentication.service.dto.AuthenticationResponse;
import com.simplecode.authentication.service.exception.ApiException;
import com.simplecode.authentication.service.exception.ErrorCode;
import com.simplecode.authentication.service.jwt.JwtTokenUtil;
import com.simplecode.authentication.service.jwt.JwtUserDetails;
import com.simplecode.authentication.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private UserService userService;


    @PostMapping("${jwt.get.token.uri}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }


    @GetMapping("${jwt.refresh.token.uri}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest httpServletRequest){
        String authToken = httpServletRequest.getHeader(tokenHeader);

        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUserDetails jwtUserDetails = (JwtUserDetails) userService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new AuthenticationResponse(token));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }


    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new ApiException(ErrorCode.USER_DISABLED, "disabled user");
        } catch (BadCredentialsException e) {
            throw new ApiException(ErrorCode.INVALID_CREDENTIALS, "invalid cre");
        }
    }

}
