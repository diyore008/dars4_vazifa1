package com.example.dars4_vazifa1.payload;

import com.sun.tracing.dtrace.ModuleAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutComeDto {

    private Integer fromCardId;
    private Integer toCardId;
    private double amount;
    private Date date;
    private double commision_amount;
}
