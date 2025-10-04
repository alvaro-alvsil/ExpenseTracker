package com.alvaro.practica.expense_tracker.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvaro.practica.expense_tracker.dtos.UserAdminDTO;
import com.alvaro.practica.expense_tracker.models.user.User;
import com.alvaro.practica.expense_tracker.services.UserService;
import com.alvaro.practica.expense_tracker.utils.ValidationErrorMapper;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService service;

    @Autowired
    private ValidationErrorMapper validator;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list-user")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@Validated @RequestBody UserAdminDTO userDTO, BindingResult result) {
        return (result.hasFieldErrors()) ? validator.validation(result) : ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "Se ha eliminado con exito el usuario con id " + id));
    }
}
