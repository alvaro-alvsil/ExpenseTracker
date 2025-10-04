package com.alvaro.practica.expense_tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvaro.practica.expense_tracker.dtos.OnCreate;
import com.alvaro.practica.expense_tracker.dtos.UserAdminDTO;
import com.alvaro.practica.expense_tracker.dtos.UserDTO;
import com.alvaro.practica.expense_tracker.services.UserService;
import com.alvaro.practica.expense_tracker.utils.ValidationErrorMapper;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private ValidationErrorMapper validator;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(OnCreate.class) @RequestBody UserDTO userDTO, BindingResult result) {
        return (result.hasFieldErrors()) ? validator.validation(result) : 
        ResponseEntity.status(HttpStatus.CREATED).body(service.save(new UserAdminDTO(userDTO.username(), userDTO.password(), false)));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated(OnCreate.class) @RequestBody UserDTO userDTO, BindingResult result) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return (result.hasFieldErrors()) ? validator.validation(result) : 
        ResponseEntity.ok(service.update(username, userDTO));
    }
}
