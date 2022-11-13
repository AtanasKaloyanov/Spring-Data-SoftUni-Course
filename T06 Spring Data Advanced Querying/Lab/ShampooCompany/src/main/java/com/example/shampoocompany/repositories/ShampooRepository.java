package com.example.shampoocompany.repositories;

import com.example.shampoocompany.entities.Shampoo;
import com.example.shampoocompany.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findByBrandAndSize(String brand, Size size);

    //Task 1
    List<Shampoo> findBySizeOrderById(Size size);

    //Task 2
    List<Shampoo> findBySizeOrLabelIdOrderByPrice(Size size, long labelId);

    //Task 3
    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    // Task 6

    int countByPriceLessThan(BigDecimal givenPrice);

    //Task 7
    @Query("SELECT s FROM Shampoo AS s " +
            "JOIN s.ingredients AS i " +
            "WHERE i.name IN :ingredients")

    List<Shampoo> findByIngredients(List<String> ingredients);

    // Task 8
    @Query("SELECT s FROM Shampoo AS s " +
            "WHERE s.ingredients.size < :numberIngredients")

    List<Shampoo> shampoosWithIngredientsLessThanGivenNumber(int numberIngredients);

}
