package com.alvaro.practica.expense_tracker.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alvaro.practica.expense_tracker.dtos.ExpenseDTO;
import com.alvaro.practica.expense_tracker.exceptions.ExpenseNotFoundException;
import com.alvaro.practica.expense_tracker.exceptions.UserNotFoundException;
import com.alvaro.practica.expense_tracker.models.expense.Expense;
import com.alvaro.practica.expense_tracker.models.user.User;
import com.alvaro.practica.expense_tracker.repositories.ExpenseRepository;
import com.alvaro.practica.expense_tracker.repositories.UserRepository;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Expense> findAll(String username) {
        return userRepository.findOneByUsername(username).map(user -> user.getExpenses())
        .orElseThrow(() -> new UserNotFoundException("No se ha encontrado usuario con el username: " + username));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Expense> listAndFilter(String username, LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByUserUsernameAndDateBetween(username, startDate, endDate);
    }

    @Override
    @Transactional
    public Expense save(ExpenseDTO expenseDto, String username) {
        Expense expense = new Expense(expenseDto);
        User user = userRepository.findOneByUsername(username).orElseThrow(() -> new UserNotFoundException("No se ha encontrado usuario con el username: " + username));
        user.addExpense(expense);
        return expenseRepository.save(expense);
    }

    @Override
    @Transactional
    public Expense update(String username, Long expenseId, ExpenseDTO expenseDto) {
        Expense expenseDb = expenseRepository.findByIdAndUserUsername(expenseId, username).orElseThrow(() -> new ExpenseNotFoundException("No se ha encontrado el gasto con id: " + expenseId + " del ususario: " + username));
        Optional.ofNullable(expenseDto.name()).ifPresent(expenseDb::setName);
        Optional.ofNullable(expenseDto.description()).ifPresent(expenseDb::setDescription);
        Optional.ofNullable(expenseDto.amount()).ifPresent(expenseDb::setAmount);
        Optional.ofNullable(expenseDto.date()).ifPresent(expenseDb::setDate);
        Optional.ofNullable(expenseDto.category()).ifPresent(expenseDb::setCategory);
        return expenseRepository.save(expenseDb);
    }

    @Override
    @Transactional
    public Expense delete(String username, Long expenseId) {
        Expense expenseDb = expenseRepository.findByIdAndUserUsername(expenseId, username).orElseThrow(() -> new ExpenseNotFoundException("No se ha encontrado el gasto con id: " + expenseId + " del ususario: " + username));
        expenseRepository.delete(expenseDb);
        return expenseDb;
    }
}
