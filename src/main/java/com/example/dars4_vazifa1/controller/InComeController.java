package com.example.dars4_vazifa1.controller;

import com.example.dars4_vazifa1.entity.InCome;
import com.example.dars4_vazifa1.service.InComeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InComeController {
    @Autowired
    InComeService inComeService;

    @GetMapping("/getInComeHistory")
    public HttpEntity<List<InCome>> getInComeHistory(){
        List<InCome> inCome = inComeService.getInCome();
        return ResponseEntity.ok(inCome);
    }
}
