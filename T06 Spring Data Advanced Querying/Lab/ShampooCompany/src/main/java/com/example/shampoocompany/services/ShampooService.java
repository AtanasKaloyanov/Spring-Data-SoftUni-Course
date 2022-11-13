package com.example.shampoocompany.services;

import com.example.shampoocompany.entities.Shampoo;
import com.example.shampoocompany.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {
    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findByBrandAndSize(String brand, Size size);
    // Task 1
    List<Shampoo> findBySizeOrderById(Size size);

    // Task 2
    List<Shampoo> findBySizeOrLabelIdOrderByPrice(Size size, long LabelId);

    //Task 3
    List<Shampoo> findByPriceGreaterThan(BigDecimal price);

    // Task 6
    int countByPriceLessThan(BigDecimal givenPrice);


    //Task 7
    List<Shampoo> findByIngredients(List<String> ingredients);

    // Task 8

    List<Shampoo> shampoosWithIngredientsLessThanGivenNumber(int numberIngredients);

}
