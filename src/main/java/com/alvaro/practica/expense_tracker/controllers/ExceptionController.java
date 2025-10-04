package com.alvaro.practica.expense_tracker.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alvaro.practica.expense_tracker.exceptions.ExpenseNotFoundException;
import com.alvaro.practica.expense_tracker.exceptions.InvalidCategoryException;
import com.alvaro.practica.expense_tracker.exceptions.UserNotFoundException;
import com.alvaro.practica.expense_tracker.exceptions.UsernameInUseException;
import com.alvaro.practica.expense_tracker.models.Error;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> userNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBuild("¡Usuario no encontrado!", ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }
    
    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<Error> expenseNotFound(ExpenseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBuild("¡Gasto no encontrado!", ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(UsernameInUseException.class)
    public ResponseEntity<Error> usernameInUse(UsernameInUseException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorBuild("¡Nombre de usuario en uso", ex.getMessage(), HttpStatus.CONFLICT.value()));
    }

    @ExceptionHandler(InvalidCategoryException.class)
    public ResponseEntity<Error> invalidCategory(InvalidCategoryException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBuild("¡Categoria invalida", ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    private Error errorBuild(String errorTittle, String message, int status) {
        Error error = new Error();
        error.setError(errorTittle);
        error.setMessage(message);
        error.setStatus(status);
        error.setDate(new Date());
        return error;
    }
}
