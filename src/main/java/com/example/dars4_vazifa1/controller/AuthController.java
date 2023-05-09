package com.example.dars4_vazifa1.controller;

import com.example.dars4_vazifa1.payload.LoginDto;
import com.example.dars4_vazifa1.security.JwtProvider;
import com.example.dars4_vazifa1.service.MyAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    MyAuthService myAuthService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public HttpEntity<?> loginToSystem(@RequestBody LoginDto loginDto){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()
            ));
            String token = jwtProvider.generateToken(loginDto.getUsername());
            return ResponseEntity.ok(token);
        }catch (BadCredentialsException e){
            return ResponseEntity.status(401).body("Login yoki Parol xato");
        }
    }
}
