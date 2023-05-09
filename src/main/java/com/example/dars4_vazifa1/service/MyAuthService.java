package com.example.dars4_vazifa1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class MyAuthService implements UserDetailsService {
    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList = new ArrayList<>(
                Arrays.asList(
                        new User("sherzod",passwordEncoder.encode("sher1234"),new ArrayList<>()),
                        new User("ziyod",passwordEncoder.encode("ziyo4545"),new ArrayList<>()),
                        new User("Saidov",passwordEncoder.encode("said0909"),new ArrayList<>()),
                        new User("Oybek",passwordEncoder.encode("oy0908"),new ArrayList<>()),
                        new User("suhrob",passwordEncoder.encode("suh7878"),new ArrayList<>())
                ));
        for (User user : userList) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        throw new UsernameNotFoundException("User not found");
    }
}