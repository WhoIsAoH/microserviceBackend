package org.srijan.expenseservice.dto;

import lombok.Data;
import org.srijan.expenseservice.entity.ExpenseCategory;
import org.srijan.expenseservice.entity.ExpenseSource;

import java.time.LocalDate;

@Data
public class ExpenseDto {
    private Double amount;

    private ExpenseCategory category;

    private String remarks;

    private String description;

    private LocalDate expenseDate;

    public static ExpenseDto from (ExpenseSource expenseSource){
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setAmount(expenseSource.getAmount());
        expenseDto.setCategory(expenseSource.getCategory());
        expenseDto.setRemarks(expenseSource.getRemarks());
        expenseDto.setDescription(expenseSource.getDescription());
        expenseDto.setExpenseDate(expenseSource.getDate());
        return expenseDto;
    }

}
