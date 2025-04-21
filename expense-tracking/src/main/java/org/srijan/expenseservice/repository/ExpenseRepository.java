package org.srijan.expenseservice.repository;

import org.srijan.expenseservice.entity.ExpenseSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseSource,Long> {
    List<ExpenseSource> findByCustomerIdAndDateBetween(Long customerId, LocalDate startDate, LocalDate endDate);

    List<ExpenseSource> findAllByCustomerId(Long customerId);

}
