package org.srijan.expenseservice.service;

import lombok.RequiredArgsConstructor;
import org.srijan.expenseservice.dto.ExpenseDto;
import org.srijan.expenseservice.entity.ExpenseSource;
import org.srijan.expenseservice.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public List<ExpenseSource> findAll() {
        return expenseRepository.findAll();
    }

    public ExpenseSource findById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public ExpenseSource save(ExpenseDto expenseDto) {
        ExpenseSource expenseSource = new ExpenseSource();
        expenseSource.setAmount(expenseDto.getAmount());
        expenseSource.setCategory(expenseDto.getCategory());
        expenseSource.setDescription(expenseDto.getDescription());
        expenseSource.setRemarks(expenseDto.getRemarks());
        return expenseRepository.save(expenseSource);
    }

    public List<ExpenseSource> expenseSourceInBetween(Long customerId, LocalDate from, LocalDate to) {
        return expenseRepository.findByCustomerIdAndDateBetween(customerId, from, to);
    }

    public Double totalExpense(Long customerId, LocalDate from, LocalDate to) {
        List<ExpenseSource> expenseSources = expenseSourceInBetween(customerId, from, to);
        Double total = 0.0;
        for (ExpenseSource expenseSource : expenseSources) {
            total += expenseSource.getAmount();
        }
        return total;
    }

    public void deleteById(Long id) {
        expenseRepository.deleteById(id);
    }


}
