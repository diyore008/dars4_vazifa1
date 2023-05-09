package com.example.dars4_vazifa1.service;

import com.example.dars4_vazifa1.entity.InCome;
import com.example.dars4_vazifa1.repository.InComeRepository;
import com.example.dars4_vazifa1.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InComeService {
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    InComeRepository inComeRepository;



    public List<InCome> getInCome(){
        String username = jwtFilter.getUsername();
        List<InCome> inComeByUsername = inComeRepository.getInComeByUsername(username);
        return inComeByUsername;
    }
}
