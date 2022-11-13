package com.example.shampoocompany.services;

import com.example.shampoocompany.entities.Ingredient;
import com.example.shampoocompany.entities.Shampoo;
import com.example.shampoocompany.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    // Task 4
    @Override
    public List<Ingredient> findByNameStartingWith(String letter) {
        return this.ingredientRepository.findByNameStartingWith(letter);
    }

    // Task 5

    @Override
    public List<Ingredient> findIngredientsByGivenList(List<String> ingredientNames) {
        return this.ingredientRepository.findIngredientsByGivenList(ingredientNames);
    }

    // Task 9
    @Override
    @Transactional
    public void deleteIngredientsByGivenName(String ingredientName) {
        this.ingredientRepository.deleteIngredientsByGivenName(ingredientName);
    }

    // Task 10

    @Override
    @Transactional
    public void setAllIngredientPricesWith10Percent() {
        this.ingredientRepository.setAllIngredientPricesWith10Percent();
    }

    // Task 11

    @Override
    @Transactional
    public void setAllIngredientsPricesByGivenList(List<String> names) {
        this.ingredientRepository.setAllIngredientsPricesByGivenList(names);
    }


}
