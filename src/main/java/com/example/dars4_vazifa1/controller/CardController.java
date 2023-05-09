package com.example.dars4_vazifa1.controller;

import com.example.dars4_vazifa1.entity.Card;
import com.example.dars4_vazifa1.payload.ApiResponse;
import com.example.dars4_vazifa1.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping("/getAllCard")
    public HttpEntity<List<Card>> getAllCard(){
        List<Card> allCard = cardService.getAllCard();
        return ResponseEntity.ok(allCard);
    }

    @GetMapping("/getCardById/{id}")
    public HttpEntity<Card> getCardById(@PathVariable Integer id){
        Card cardById = cardService.getCardById(id);
        return ResponseEntity.ok(cardById);
    }

    @PostMapping("/addCard")
    public HttpEntity<ApiResponse> addCard(@RequestBody Card addCard){
        ApiResponse apiResponse = cardService.addCard(addCard);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/updateCard/{id}")
    public HttpEntity<ApiResponse> updateCard(@PathVariable Integer id, @RequestBody Card card){
        ApiResponse apiResponse = cardService.updateCard(id, card);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/deleteCard/{id}")
    public HttpEntity<ApiResponse> deleteCard(@PathVariable Integer id){
        ApiResponse apiResponse = cardService.deleteCard(id);
        return ResponseEntity.status(apiResponse.isSuccess()?401:202).body(apiResponse);
    }


}
