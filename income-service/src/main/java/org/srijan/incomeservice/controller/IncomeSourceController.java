package org.srijan.incomeservice.controller;

import lombok.RequiredArgsConstructor;
import org.srijan.incomeservice.dto.IncomeDto;
import org.srijan.incomeservice.entity.IncomeSource;
import org.srijan.incomeservice.service.IncomeSourceService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/income")
public class IncomeSourceController {

    private final IncomeSourceService incomeSourceService;

    @GetMapping("/")
    public List<IncomeSource> getAllIncomeSources() {
        return incomeSourceService.findAllByCustomerId();
    }

    @GetMapping("/getById")
    public IncomeSource getIncomeSourceById(@RequestParam Long id) {
        return incomeSourceService.findById(id);
    }

    @PostMapping("/save")
    public IncomeSource createIncomeSource(@RequestBody IncomeDto incomeSource) {
        return incomeSourceService.save(incomeSource);
    }

    @GetMapping("/inBetween")
    public List<IncomeSource> getIncomeSourceBetween(@RequestBody Long customerId, @RequestParam LocalDate start, @RequestParam LocalDate end) {
        return incomeSourceService.findByCustomerIdAndDateBetween(customerId, start, end);
    }

    @GetMapping("/totalIncome")
    public double getTotalIncomeBetween(@RequestBody Long customerId, @RequestParam LocalDate start, @RequestParam LocalDate end) {
        return incomeSourceService.totalIncomeForDate(customerId, start, end);
    }

    @DeleteMapping("/")
    public void deleteIncomeSource(@RequestBody Long id) {
        incomeSourceService.deleteById(id);
    }

}
