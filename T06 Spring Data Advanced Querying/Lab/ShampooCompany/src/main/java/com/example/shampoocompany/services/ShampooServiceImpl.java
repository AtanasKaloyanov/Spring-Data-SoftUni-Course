package com.example.shampoocompany.services;

import com.example.shampoocompany.entities.Shampoo;
import com.example.shampoocompany.entities.Size;
import com.example.shampoocompany.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService{
    final private ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findByBrand(String brand) {
        return this.shampooRepository.findByBrand(brand);
    }

    @Override
    public List<Shampoo> findByBrandAndSize(String brand, Size size) {
        return this.shampooRepository.findByBrandAndSize(brand, size);
    }

    // Task 1
    @Override
    public List<Shampoo> findBySizeOrderById(Size size) {
        return shampooRepository.findBySizeOrderById(size);
    }

    // Task 2
    @Override
    public List<Shampoo> findBySizeOrLabelIdOrderByPrice(Size size, long labelId) {
        return this.shampooRepository.findBySizeOrLabelIdOrderByPrice(size, labelId);
    }

    // Task 3
    @Override
    public List<Shampoo> findByPriceGreaterThan(BigDecimal price) {
        return this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price);
    }

    // Task 6
    @Override
    public int countByPriceLessThan(BigDecimal givenPrice) {
        return this.shampooRepository.countByPriceLessThan(givenPrice);
    }

    // Task 7
    @Override
    public List<Shampoo> findByIngredients(List<String> ingredients) {
        return this.shampooRepository.findByIngredients(ingredients);
    }

    // Task 8
    @Override
    public List<Shampoo> shampoosWithIngredientsLessThanGivenNumber(int numberIngredients) {
        return this.shampooRepository.shampoosWithIngredientsLessThanGivenNumber(numberIngredients);
    }
}
