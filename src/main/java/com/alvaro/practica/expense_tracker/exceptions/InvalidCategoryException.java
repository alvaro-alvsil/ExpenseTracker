package com.alvaro.practica.expense_tracker.exceptions;

public class InvalidCategoryException extends RuntimeException {

    public InvalidCategoryException(String message) {
        super(message);
    }
}
