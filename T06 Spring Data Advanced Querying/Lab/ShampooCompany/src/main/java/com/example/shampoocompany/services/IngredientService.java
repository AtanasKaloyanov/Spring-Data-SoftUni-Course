package com.example.shampoocompany.services;

import com.example.shampoocompany.entities.Ingredient;
import com.example.shampoocompany.entities.Shampoo;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {
    // Task 4
    List<Ingredient> findByNameStartingWith(String letter);

    // Task 5
    List<Ingredient> findIngredientsByGivenList(List<String> ingredientNames);

// Task 9
void deleteIngredientsByGivenName(String ingredientName);

// Task 10
void setAllIngredientPricesWith10Percent();

// Task 11
void setAllIngredientsPricesByGivenList(List<String> names);
}
