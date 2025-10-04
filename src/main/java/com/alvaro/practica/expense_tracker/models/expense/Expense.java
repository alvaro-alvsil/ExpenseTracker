package com.alvaro.practica.expense_tracker.models.expense;

import java.time.LocalDate;

import com.alvaro.practica.expense_tracker.dtos.ExpenseDTO;
import com.alvaro.practica.expense_tracker.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double amount;

    private LocalDate date;

    private ExpenseCategory category;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    @JsonIgnore
    private User user;

    public Expense() {
    }
    
    public Expense(ExpenseDTO dto) {
        this.name = dto.name();
        this.description = dto.description();
        this.amount = dto.amount();
        this.date = dto.date();
        this.category = dto.category();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
