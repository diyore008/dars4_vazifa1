package com.example.dars4_vazifa1.service;

import com.example.dars4_vazifa1.entity.Card;
import com.example.dars4_vazifa1.entity.InCome;
import com.example.dars4_vazifa1.entity.OutCome;
import com.example.dars4_vazifa1.payload.ApiResponse;
import com.example.dars4_vazifa1.payload.OutComeDto;
import com.example.dars4_vazifa1.repository.CardRepository;
import com.example.dars4_vazifa1.repository.InComeRepository;
import com.example.dars4_vazifa1.repository.OutComeRepository;
import com.example.dars4_vazifa1.security.JwtFilter;
import com.example.dars4_vazifa1.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OutComeService {
    private double commision_amount = 0.01;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OutComeRepository outComeRepository;
    @Autowired
    InComeRepository inComeRepository;
    @Autowired
    JwtFilter jwtFilter;
    public ApiResponse sendMoney(OutComeDto outComeDto){
        Optional<Card> optionalCard = cardRepository.findById(outComeDto.getFromCardId());
        if (!optionalCard.isPresent())
            return new ApiResponse("Sizda unday karta yo'q", false);
        Optional<Card> optionalCard1 = cardRepository.findById(outComeDto.getToCardId());
        if (!optionalCard1.isPresent())
            return new ApiResponse("Siz jo'natayotgan karta topilmadi", false);

        //--Tizimga kirgan odamni kartasi shu ekanligini tekshirish
        //--Bazadagi Card<username> si tizimga kirgan user username si teng bolishi kerak
        String username = jwtFilter.getUsername();
        if (!optionalCard.get().getUsername().equals(username)){
            return new ApiResponse("Sizda bunday karta yoq", false);
        }



        OutCome outCome = new OutCome();

        outCome.setFromCard(optionalCard.get());
        outCome.setToCard(optionalCard1.get());
        outCome.setAmount(outComeDto.getAmount());
        outCome.setDate(new Date());

        //Balans tekshirish, yechilayotgan va kelayotgan pulni update qilish
        double balance = optionalCard.get().getBalance();
        double commision = outComeDto.getAmount() * commision_amount;

        //Balance tekshirish va shart bajarilsa pul yechish
        if (balance >= (outComeDto.getAmount()+ commision)){
            balance = balance - commision - outComeDto.getAmount();
            Card card = optionalCard.get();
            card.setBalance(balance);
            cardRepository.save(card);

            //Pul otkazilayotgan kartani balance siga otkazilayotgan pulni qoshdik
            double balance1 = optionalCard1.get().getBalance();
            balance1 = balance1 + outComeDto.getAmount();
            card = optionalCard1.get();
            card.setBalance(balance1);
            cardRepository.save(card);

            //Bu pul tushayotgan kartani tarixini yozadi
            InCome inCome = new InCome();
            inCome.setFromCard(optionalCard.get());
            inCome.setToCard(optionalCard1.get());
            inCome.setAmount(outComeDto.getAmount());
            inCome.setDate(new Date());
            inComeRepository.save(inCome);

            outComeRepository.save(outCome);

            return new ApiResponse("Sent",true);
        }
        return new ApiResponse("You don't have enough money",false);

    }

    public List<OutCome> getOutComeHistory(){

        String username = jwtFilter.getUsername();
        List<OutCome> outComesByUsername = outComeRepository.getOutComesByUsername(username);

        return outComesByUsername;
    }
}


