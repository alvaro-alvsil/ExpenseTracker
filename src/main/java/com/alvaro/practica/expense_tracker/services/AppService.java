package com.alvaro.practica.expense_tracker.services;

import java.time.LocalDate;
import java.util.List;

import com.alvaro.practica.expense_tracker.dtos.ExpenseDTO;
import com.alvaro.practica.expense_tracker.models.expense.Expense;

public interface AppService {

    List<Expense> findAll(String username);

    List<Expense> listAndFilter(String username, LocalDate startDate, LocalDate endDate);

    Expense save(ExpenseDTO expense, String username);

    Expense update(String username, Long expenseId, ExpenseDTO expenseDto);
    
    Expense delete(String username, Long expenseId);
}
