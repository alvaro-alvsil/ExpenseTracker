package com.alvaro.practica.expense_tracker.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserAdminDTO(
    
    @NotBlank
    @Size(min = 3, max = 20, groups = {OnCreate.class, OnUpdate.class})
    String username,

    @NotBlank
    @Size(min = 5, max = 20, groups = {OnCreate.class, OnUpdate.class})
    String password,

    boolean isAdmin
) {}
