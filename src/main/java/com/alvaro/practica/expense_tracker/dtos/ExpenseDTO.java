package com.alvaro.practica.expense_tracker.dtos;

import java.time.LocalDate;

import com.alvaro.practica.expense_tracker.models.expense.ExpenseCategory;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ExpenseDTO(
    @NotBlank(groups = OnCreate.class)
    @Size(max = 20, groups = {OnCreate.class, OnUpdate.class})
    String name, 
    
    @NotBlank(groups = OnCreate.class)
    @Size(max = 40, groups = {OnCreate.class, OnUpdate.class})
    String description, 
    
    @NotNull(groups = OnCreate.class)
    @DecimalMin(value = "0.01", groups = {OnCreate.class, OnUpdate.class})
    Double amount, 
    
    @NotNull(groups = OnCreate.class)
    LocalDate date, 
    
    @NotNull(groups = OnCreate.class)
    ExpenseCategory category
) {}
