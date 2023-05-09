package com.example.dars4_vazifa1.service;

import com.example.dars4_vazifa1.entity.Card;
import com.example.dars4_vazifa1.payload.ApiResponse;
import com.example.dars4_vazifa1.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    public List<Card> getAllCard(){
        List<Card> cardList = cardRepository.findAll();
        return cardList;
    }

    public Card getCardById(Integer id){
        Optional<Card> optionalCard = cardRepository.findById(id);
        return optionalCard.get();
    }

    public ApiResponse addCard(Card addCard){

        try{
            Card card = new Card();
        card.setUsername(addCard.getUsername());
        card.setNumber(addCard.getNumber());
        card.setBalance(addCard.getBalance());
        card.setExpireDate(addCard.getExpireDate());
        card.setActive(addCard.isActive());
        cardRepository.save(card);
        return new ApiResponse("Saved card", true);
        }catch (Exception e){
            return new ApiResponse("Error", false);
        }
    }


    public ApiResponse updateCard(Integer id, Card card){
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent())
            return new ApiResponse("Card not found", false);
        try {
            Card upCard = optionalCard.get();

            upCard.setUsername(card.getUsername());
            upCard.setNumber(card.getNumber());
            upCard.setBalance(card.getBalance());
            upCard.setExpireDate(card.getExpireDate());
            upCard.setActive(card.isActive());
            cardRepository.save(upCard);
            return new ApiResponse("Update card", true);
        }catch (Error e){
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse deleteCard(Integer id){
        try {
            cardRepository.deleteById(id);
            return new ApiResponse("Deleted card",true);
        }catch (Error e){
            return new ApiResponse("Card not found", false);
        }
    }
}
