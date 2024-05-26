package com.individual.foodmotion.foodproductsservice;

import com.individual.foodmotion.foodproductsservice.model.*;
import com.individual.foodmotion.foodproductsservice.repository.FoodProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodProductsServiceApplication implements CommandLineRunner {

    @Autowired
    private FoodProductRepository foodProductRepository;

    public static void main(String[] args) {
        SpringApplication.run(FoodProductsServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create and set up the Nutrition components for Bread
        Protein breadProtein = new Protein();
        breadProtein.setTotalProtein(9);

        Fat breadFat = new Fat();
        breadFat.setTotalFat(1);
        breadFat.setTransFat(0);

        Carbs breadCarbs = new Carbs();
        breadCarbs.setTotalCarbs(50);
        breadCarbs.setSugar(5);

        Nutrition breadNutrition = new Nutrition();
        breadNutrition.setProtein(breadProtein);
        breadNutrition.setFat(breadFat);
        breadNutrition.setCarbs(breadCarbs);

        DietaryRestrictions breadDietaryRestrictions = new DietaryRestrictions();
        breadDietaryRestrictions.setGlutenFree(false);
        breadDietaryRestrictions.setLactoseFree(true);
        breadDietaryRestrictions.setVegan(true);
        breadDietaryRestrictions.setVegetarian(true);

        FoodProduct bread = new FoodProduct();
        bread.setName("Bread");
        bread.setNutrition(breadNutrition);
        bread.setDietaryRestrictions(breadDietaryRestrictions);

        // Set bidirectional relationships for Bread
        breadProtein.setNutrition(breadNutrition);
        breadFat.setNutrition(breadNutrition);
        breadCarbs.setNutrition(breadNutrition);
        breadNutrition.setFoodProduct(bread);
        breadDietaryRestrictions.setFoodProduct(bread);

        // Create and set up the Nutrition components for Eggs
        Protein eggsProtein = new Protein();
        eggsProtein.setTotalProtein(13);

        Fat eggsFat = new Fat();
        eggsFat.setTotalFat(11);
        eggsFat.setTransFat(5.7);

        Carbs eggsCarbs = new Carbs();
        eggsCarbs.setTotalCarbs(1);
        eggsCarbs.setSugar(1);

        Nutrition eggsNutrition = new Nutrition();
        eggsNutrition.setProtein(eggsProtein);
        eggsNutrition.setFat(eggsFat);
        eggsNutrition.setCarbs(eggsCarbs);

        DietaryRestrictions eggsDietaryRestrictions = new DietaryRestrictions();
        eggsDietaryRestrictions.setGlutenFree(true);
        eggsDietaryRestrictions.setLactoseFree(true);
        eggsDietaryRestrictions.setVegan(false);
        eggsDietaryRestrictions.setVegetarian(false);

        FoodProduct eggs = new FoodProduct();
        eggs.setName("Eggs");
        eggs.setNutrition(eggsNutrition);
        eggs.setDietaryRestrictions(eggsDietaryRestrictions);

        // Set bidirectional relationships for Eggs
        eggsProtein.setNutrition(eggsNutrition);
        eggsFat.setNutrition(eggsNutrition);
        eggsCarbs.setNutrition(eggsNutrition);
        eggsNutrition.setFoodProduct(eggs);
        eggsDietaryRestrictions.setFoodProduct(eggs);

        // Create and set up the Nutrition components for Apple
        Protein appleProtein = new Protein();
        appleProtein.setTotalProtein(0.3);

        Fat appleFat = new Fat();
        appleFat.setTotalFat(0.2);
        appleFat.setTransFat(0.1);

        Carbs appleCarbs = new Carbs();
        appleCarbs.setTotalCarbs(25);
        appleCarbs.setSugar(19);

        Nutrition appleNutrition = new Nutrition();
        appleNutrition.setProtein(appleProtein);
        appleNutrition.setFat(appleFat);
        appleNutrition.setCarbs(appleCarbs);

        DietaryRestrictions appleDietaryRestrictions = new DietaryRestrictions();
        appleDietaryRestrictions.setGlutenFree(true);
        appleDietaryRestrictions.setLactoseFree(true);
        appleDietaryRestrictions.setVegan(true);
        appleDietaryRestrictions.setVegetarian(true);

        FoodProduct apple = new FoodProduct();
        apple.setName("Apple");
        apple.setNutrition(appleNutrition);
        apple.setDietaryRestrictions(appleDietaryRestrictions);

        // Set bidirectional relationships for Apple
        appleProtein.setNutrition(appleNutrition);
        appleFat.setNutrition(appleNutrition);
        appleCarbs.setNutrition(appleNutrition);
        appleNutrition.setFoodProduct(apple);
        appleDietaryRestrictions.setFoodProduct(apple);

        // Create and set up the Nutrition components for Milk
        Protein milkProtein = new Protein();
        milkProtein.setTotalProtein(3.4);

        Fat milkFat = new Fat();
        milkFat.setTotalFat(1.3);
        milkFat.setTransFat(1.1);

        Carbs milkCarbs = new Carbs();
        milkCarbs.setTotalCarbs(5);
        milkCarbs.setSugar(5);

        Nutrition milkNutrition = new Nutrition();
        milkNutrition.setProtein(milkProtein);
        milkNutrition.setFat(milkFat);
        milkNutrition.setCarbs(milkCarbs);

        DietaryRestrictions milkDietaryRestrictions = new DietaryRestrictions();
        milkDietaryRestrictions.setGlutenFree(true);
        milkDietaryRestrictions.setLactoseFree(false);
        milkDietaryRestrictions.setVegan(false);
        milkDietaryRestrictions.setVegetarian(true);

        FoodProduct milk = new FoodProduct();
        milk.setName("Milk");
        milk.setNutrition(milkNutrition);
        milk.setDietaryRestrictions(milkDietaryRestrictions);

        // Set bidirectional relationships for Milk
        milkProtein.setNutrition(milkNutrition);
        milkFat.setNutrition(milkNutrition);
        milkCarbs.setNutrition(milkNutrition);
        milkNutrition.setFoodProduct(milk);
        milkDietaryRestrictions.setFoodProduct(milk);

        // Save products
        foodProductRepository.save(bread);
        foodProductRepository.save(eggs);
        foodProductRepository.save(apple);
        foodProductRepository.save(milk);
    }
}