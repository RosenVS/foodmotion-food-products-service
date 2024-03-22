package com.individual.foodmotion.foodproductsservice.controller;


import com.individual.foodmotion.foodproductsservice.dto.requests.FoodProductRequestDTO;
import com.individual.foodmotion.foodproductsservice.dto.responses.FoodProductResponseDTO;
import com.individual.foodmotion.foodproductsservice.service.FoodProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-product")
@RequiredArgsConstructor
public class FoodProductsController {
    private final FoodProductService foodProductService;

    @GetMapping("test")
    public ResponseEntity<String> getHello() {
        // Change the return value to "hello world"
        return ResponseEntity.ok("hello world v2 demo");
    }

    @GetMapping
    public ResponseEntity<List<FoodProductResponseDTO>> getAllFoodProducts() {
        List<FoodProductResponseDTO> foodProducts = foodProductService.getAllFoodProducts();
        return ResponseEntity.ok(foodProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodProductResponseDTO> getFoodProductById(@PathVariable Long id) {
        FoodProductResponseDTO foodProduct = foodProductService.getFoodProductById(id);
        return ResponseEntity.ok(foodProduct);
    }

    @PostMapping
    public ResponseEntity<FoodProductResponseDTO> createFoodProduct(@RequestBody FoodProductRequestDTO foodProductRequestDTO) {
        FoodProductResponseDTO createdFoodProduct = foodProductService.createFoodProduct(foodProductRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFoodProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodProductResponseDTO> updateFoodProduct(@PathVariable Long id, @RequestBody FoodProductRequestDTO foodProductRequestDTO) {
        FoodProductResponseDTO updatedFoodProduct = foodProductService.updateFoodProduct(id, foodProductRequestDTO);
        return ResponseEntity.ok(updatedFoodProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteFoodProduct(@PathVariable Long id) {
        boolean deletionSuccessful = foodProductService.deleteFoodProduct(id);
        return ResponseEntity.ok(deletionSuccessful);
    }


}
