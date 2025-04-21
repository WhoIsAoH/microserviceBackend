package org.srijan.incomeservice.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import org.srijan.incomeservice.config.JwtUtil;
import org.srijan.incomeservice.dto.IncomeDto;
import org.srijan.incomeservice.entity.IncomeSource;
import org.srijan.incomeservice.repository.IncomeSourceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeSourceService {

    private final IncomeSourceRepository incomeSourceRepository;
    private final WebClient webClient;
    private final JwtUtil jwtUtil;
    private final HttpServletRequest request;

    public List<IncomeSource> findAllByCustomerId() {
        return incomeSourceRepository.findAllByCustomerId(1L);
    }

    public List<IncomeSource> findByCustomerIdAndDateBetween(Long customerId, LocalDate start, LocalDate end) {
        return incomeSourceRepository.findByCustomerIdAndDateBetween(customerId,start,end);
    }

    public Double totalIncomeForDate(Long customerId, LocalDate start, LocalDate end) {
        List<IncomeSource> incomeList = incomeSourceRepository.findByCustomerIdAndDateBetween(customerId,start,end);
        Double totalIncome = 0.0;
        for(IncomeSource incomeSource : incomeList){
            totalIncome += incomeSource.getAmount();
        }
        return totalIncome;
    }

    public IncomeSource findById(Long id) {
        return incomeSourceRepository.findById(id).orElse(null);
    }

    public IncomeSource save(IncomeDto incomeDto) {
        IncomeSource incomeSource = new IncomeSource();
        incomeSource.setAmount(incomeDto.getAmount());
        incomeSource.setDate(incomeDto.getDate());
        incomeSource.setRemarks(incomeDto.getRemarks());
        incomeSource.setCustomerId(jwtUtil.extractUserId(request.getHeader("Authorization").substring(7)));
        return incomeSourceRepository.save(incomeSource);
    }

    public void deleteById(Long id) {
        incomeSourceRepository.deleteById(id);
    }

}
