package com.example.dars4_vazifa1.repository;

import com.example.dars4_vazifa1.entity.InCome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InComeRepository extends JpaRepository<InCome,Integer> {

    @Query(value = "select *\n"+
    "from in_come o \n"+
    " join  card c on c.id = o.to_card_id \n"+
    "where c.username = :username1", nativeQuery = true)
    List<InCome> getInComeByUsername(@Param("username1") String username1);
}
