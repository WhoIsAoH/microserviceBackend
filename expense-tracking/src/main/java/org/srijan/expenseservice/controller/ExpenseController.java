package org.srijan.expenseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.srijan.expenseservice.dto.ExpenseDto;
import org.srijan.expenseservice.entity.ExpenseSource;
import org.srijan.expenseservice.service.ExpenseService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/")
    public List<ExpenseSource> getExpenseSources() {
        return expenseService.findAll();
    }

    @GetMapping("/getById")
    public ExpenseSource getExpenseSourceById(@RequestBody Long id) {
        return expenseService.findById(id);
    }

    @PostMapping("/")
    public ExpenseSource saveExpenseSource(@RequestBody ExpenseDto expenseDto) {
        return expenseService.save(expenseDto);
    }

    @GetMapping("/totalExpense")
    public Double totalExpense(@RequestBody Long customerId,@RequestBody LocalDate from,@RequestBody LocalDate to) {
        return expenseService.totalExpense(customerId, from, to);
    }

    @GetMapping("/getInBetween")
    public List<ExpenseSource> getExpenseInBetween(@RequestBody Long customerId,@RequestBody LocalDate from,@RequestBody LocalDate to) {
        return expenseService.expenseSourceInBetween(customerId, from, to);
    }

    @DeleteMapping("/")
    public void deleteExpenseSource(@RequestBody Long id) {
        expenseService.deleteById(id);
    }

}
