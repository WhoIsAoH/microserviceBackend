package org.srijan.incomeservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeDto {

    private Double amount;

    private LocalDate date;

    private String remarks;


//    public static IncomeDto from (IncomeSource source) {
//        IncomeDto dto = new IncomeDto();
//        dto.amount = source.getAmount();
//        dto.date = source.getDate();
//        dto.remarks = source.getRemarks();
//        return dto;
//    }
}
