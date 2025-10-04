package com.alvaro.practica.expense_tracker.controllers;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alvaro.practica.expense_tracker.dtos.ExpenseDTO;
import com.alvaro.practica.expense_tracker.dtos.OnCreate;
import com.alvaro.practica.expense_tracker.dtos.OnUpdate;
import com.alvaro.practica.expense_tracker.services.AppService;
import com.alvaro.practica.expense_tracker.utils.ValidationErrorMapper;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private AppService appService;

    @Autowired
    private ValidationErrorMapper validator;

    @GetMapping
    public ResponseEntity<?> expensesList(@RequestParam(required = false) LocalDate startDate, @RequestParam(required = false) LocalDate endDate) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(startDate == null && endDate != null) {
            return ResponseEntity.badRequest().body(Map.of("error", "No se puede dar una fecha de fin sin una fecha de inicio"));
        }

        if(startDate == null)
            return ResponseEntity.ok(appService.findAll(username));
        
        if (endDate != null && startDate.isAfter(endDate)) {
            return ResponseEntity.badRequest().body(Map.of("error", "La fecha de inicio no puede ser posterior a la fecha de fin"));
        }
        
        endDate = (endDate != null) ? endDate : LocalDate.now();
        return ResponseEntity.ok(appService.listAndFilter(username, startDate, endDate));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addExpense(@Validated(OnCreate.class) @RequestBody ExpenseDTO expenseDTO, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validator.validation(result);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(appService.save(expenseDTO, username));
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<?> update(@PathVariable Long expenseId, @Validated(OnUpdate.class) @RequestBody ExpenseDTO expenseDTO, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validator.validation(result);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(appService.update(username, expenseId, expenseDTO));
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<?> remove(@PathVariable Long expenseId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(appService.delete(username, expenseId));
    }
}
