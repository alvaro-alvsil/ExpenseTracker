package com.alvaro.practica.expense_tracker.models.expense;

import com.alvaro.practica.expense_tracker.exceptions.InvalidCategoryException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ExpenseCategory {
    ALIMENTACION("alimentacion"),
    OCIO("ocio"),
    ELECTRONICA("electronica"),
    SERVICIOS("servicios"),
    ROPA("ropa"),
    SALUD("salud"),
    OTROS("otros");

    private final String category;

    ExpenseCategory(String categoria) {
        this.category = categoria;
    }

    @JsonValue
    public String getCategory() {
        return category;
    }

    @JsonCreator
    public static ExpenseCategory fromString(String value) {
        for (ExpenseCategory category : ExpenseCategory.values()) {
            if (category.category.equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new InvalidCategoryException("Invalid category: " + value); 
    }
}
