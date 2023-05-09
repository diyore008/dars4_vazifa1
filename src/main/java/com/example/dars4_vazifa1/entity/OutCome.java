package com.example.dars4_vazifa1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OutCome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Card fromCard;
    @ManyToOne
    private Card toCard;
    private double amount;
    private Date date;
    private double commision_amount;
}
