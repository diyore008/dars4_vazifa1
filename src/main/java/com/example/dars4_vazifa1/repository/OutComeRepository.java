package com.example.dars4_vazifa1.repository;

import com.example.dars4_vazifa1.entity.OutCome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OutComeRepository extends JpaRepository<OutCome,Integer> {
    @Query(value = "select *\n"+
    "from out_come o \n"+
            " join card c on c.id = o.from_card_id\n"+
    " where c.username =  :username1", nativeQuery = true)
    List<OutCome> getOutComesByUsername(@Param("username1") String username1);

    List<OutCome> getOutComesById(Integer id);
}
