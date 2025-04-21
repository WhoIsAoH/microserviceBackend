package org.srijan.incomeservice.repository;

import org.srijan.incomeservice.entity.IncomeSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomeSourceRepository extends JpaRepository<IncomeSource, Long> {

    List<IncomeSource> findByCustomerIdAndDateBetween(Long customerId, LocalDate startDate, LocalDate endDate);

    List<IncomeSource> findAllByCustomerId(Long customerId);

}
