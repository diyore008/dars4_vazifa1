package com.example.dars4_vazifa1.repository;

import com.example.dars4_vazifa1.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Integer> {


}
