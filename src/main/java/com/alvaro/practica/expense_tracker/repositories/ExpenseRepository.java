package com.alvaro.practica.expense_tracker.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alvaro.practica.expense_tracker.models.expense.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserId(Long id);

    List<Expense> findByUserUsernameAndDateBetween(String username, LocalDate startDate, LocalDate endDate);

    Optional<Expense> findByIdAndUserUsername(Long expenseId, String username);
}
