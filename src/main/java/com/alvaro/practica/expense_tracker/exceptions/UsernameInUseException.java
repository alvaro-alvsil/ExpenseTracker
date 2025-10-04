package com.alvaro.practica.expense_tracker.exceptions;

public class UsernameInUseException extends RuntimeException {

     public UsernameInUseException(String message) {
        super(message);
     }
}
