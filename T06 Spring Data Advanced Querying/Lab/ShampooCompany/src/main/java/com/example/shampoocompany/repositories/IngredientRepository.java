package com.example.shampoocompany.repositories;

import com.example.shampoocompany.entities.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    // Task 4
    List<Ingredient> findByNameStartingWith(String letter);

    // Task 5
    @Query("SELECT i FROM Ingredient AS i " +
    "WHERE i.name IN :ingredientNames " +
    "ORDER BY i.price")

    List<Ingredient> findIngredientsByGivenList(List<String> ingredientNames);

    // Task 9
    @Query("DELETE FROM Ingredient AS i " +
            "WHERE i.name = :ingredientName")
    @Modifying

    void deleteIngredientsByGivenName(String ingredientName);

    // Task 10
    @Query("UPDATE Ingredient AS i " +
            "SET i.price = 1.1 * i.price")
    @Modifying

    void setAllIngredientPricesWith10Percent();

    // Task 11
    @Query("UPDATE Ingredient AS i " +
    "SET i.price = 1.1 * i.price " +
    "WHERE i.name IN :names")
    @Modifying

    void setAllIngredientsPricesByGivenList(List<String> names);
}
