package com.example.dars4_vazifa1.controller;

import com.example.dars4_vazifa1.entity.OutCome;
import com.example.dars4_vazifa1.payload.ApiResponse;
import com.example.dars4_vazifa1.payload.OutComeDto;
import com.example.dars4_vazifa1.service.OutComeService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.HtmlEscapeTag;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OutComeController {

    @Autowired
    OutComeService outComeService;

    @PostMapping("/sendMoney")
    public HttpEntity<ApiResponse> sendMoney(@RequestBody OutComeDto outComeDto){
        ApiResponse apiResponse = outComeService.sendMoney(outComeDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/getHistory")
    public HttpEntity<List<OutCome>> getOutPutHistory(){
        List<OutCome> outComeHistory = outComeService.getOutComeHistory();
        return ResponseEntity.ok(outComeHistory);
    }

}
