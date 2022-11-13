package com.example.shampoocompany;

import com.example.shampoocompany.entities.Ingredient;
import com.example.shampoocompany.entities.Shampoo;
import com.example.shampoocompany.services.IngredientService;
import com.example.shampoocompany.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class Main implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public Main(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }


    @Override
    public void run(String... args) throws Exception {

//        List<Shampoo> shampoosByBrand = this.shampooService.findByBrand("Cotton Fresh");
//
//        for (Shampoo currentShampoo : shampoosByBrand) {
//            System.out.println(currentShampoo.getId());
//        }

// List<Shampoo> shampoosByBrandAndSize = this.shampooService.findByBrandAndSize("Cotton Fresh", Size.SMALL);
//
// for (Shampoo currentShampoo : shampoosByBrandAndSize) {
//     System.out.println(currentShampoo);
// }


        // Task 1
//        Scanner scanner = new Scanner(System.in);
//        String givenSize = scanner.nextLine();
//
//        List<Shampoo> shampoosBySize = this.shampooService.findBySizeOrderById(Size.valueOf(givenSize));
//
//        for (Shampoo currentShampoo : shampoosBySize) {
//            System.out.println(currentShampoo);
//        }

        // Task 2
//        Scanner scanner = new Scanner(System.in);
//        String givenSizeString = scanner.nextLine();
//        Size size = Size.valueOf(givenSizeString);
//
//        long labelId = Long.parseLong(scanner.nextLine());
//
//        List<Shampoo> shampoosBySizeOrLabel = this.shampooService.findBySizeOrLabelIdOrderByPrice(size, labelId);
//
//        for (Shampoo currentShampoo : shampoosBySizeOrLabel) {
//            System.out.println(currentShampoo);
//        }

        // Task 3
//        Scanner scanner = new Scanner(System.in);
//        BigDecimal givenPrice = BigDecimal.valueOf(Long.parseLong(scanner.nextLine()));
//
//        List<Shampoo> shampoosMoreExpensiveThanGivenPrice = this.shampooService.findByPriceGreaterThan(givenPrice);
//
//        for (Shampoo currentShampoo : shampoosMoreExpensiveThanGivenPrice) {
//            System.out.println(currentShampoo);
//        }

        // Task 4
//        Scanner scanner = new Scanner(System.in);
//        String letter = scanner.nextLine();
//
//        List<Ingredient> ingredientsStartingWithGivenLetter = this.ingredientService.findByNameStartingWith(letter);
//
//        for (Ingredient ingredient : ingredientsStartingWithGivenLetter) {
//            System.out.println(ingredient);
//        }

        //Task 5
//   Scanner scanner = new Scanner(System.in);
//   List<String> ingredietNames = new ArrayList<>();
//
//   String input = scanner.nextLine();
//
//   while (!input.isEmpty()) {
//       ingredietNames.add(input);
//
//       input = scanner.nextLine();
//   }
//
//   List<Ingredient> ingredientList = this.ingredientService.findIngredientsByGivenList(ingredietNames);
//
//   for (Ingredient currentIngredient : ingredientList) {
//       System.out.println(currentIngredient);
//   }

     //    Task 6
//        Scanner scanner = new Scanner(System.in);
//        BigDecimal givenPrice = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
//
//        int countCheepererShampoosByGivenPrice = this.shampooService.countByPriceLessThan(givenPrice);
//
//        System.out.println(countCheepererShampoosByGivenPrice);

//        // Task 7
//        Scanner scanner = new Scanner(System.in);
//
//        String input = scanner.nextLine();
//
//        List<String> ingredients = new ArrayList<>();
//
//        while (!input.isBlank()) {
//            ingredients.add(input);
//            input = scanner.nextLine();
//        }
//
//        List<Shampoo> shampoosByIngredients = this.shampooService.findByIngredients(ingredients);
//
//        for (Shampoo shampoosByIngredient : shampoosByIngredients) {
//            System.out.println(shampoosByIngredient.getBrand());
//        }

        // Task 8
//
//        Scanner scanner = new Scanner(System.in);
//        int givenNumber = Integer.parseInt(scanner.nextLine());
//
//        List<Shampoo> shampoosWhithLessIngredientsThanGivenNumber = this.shampooService.shampoosWithIngredientsLessThanGivenNumber(givenNumber);
//
//        for (Shampoo currentShampoo : shampoosWhithLessIngredientsThanGivenNumber) {
//            System.out.println(currentShampoo.getBrand());
//        }

        // Task 9
//        Scanner scanner = new Scanner(System.in);
//        String givenName = scanner.nextLine();
//
//        this.ingredientService.deleteIngredientsByGivenName(givenName);

        // Task 10
      //  this.ingredientService.setAllIngredientPricesWith10Percent();

        // Task 11
        List<String> givenList = new ArrayList<>(Arrays.asList("Macadamia Oil", "Aloe Vera"));

        this.ingredientService.setAllIngredientsPricesByGivenList(givenList);
    }
}
