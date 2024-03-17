package com.individual.foodmotion.foodproductsservice.service;

import com.individual.foodmotion.foodproductsservice.dto.requests.FoodProductRequestDTO;
import com.individual.foodmotion.foodproductsservice.dto.responses.FoodProductResponseDTO;
import com.individual.foodmotion.foodproductsservice.exception.ResourceNotFoundException;
import com.individual.foodmotion.foodproductsservice.map.FoodProductMapper;
import com.individual.foodmotion.foodproductsservice.model.DietaryRestrictions;
import com.individual.foodmotion.foodproductsservice.model.FoodProduct;
import com.individual.foodmotion.foodproductsservice.model.Nutrition;
import com.individual.foodmotion.foodproductsservice.repository.FoodProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FoodProductService {
    private final FoodProductRepository foodProductRepository;
    private final FoodProductMapper foodProductMapper;
    public List<FoodProductResponseDTO> getAllFoodProducts() {
        List<FoodProduct> foodProducts = foodProductRepository.findAll();
        return foodProducts.stream()
                .map(foodProductMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public FoodProductResponseDTO getFoodProductById(Long id) {
        FoodProduct foodProduct = foodProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodProduct Not Found"));

        return foodProductMapper.mapToResponseDto(foodProduct);
    }

    public FoodProductResponseDTO createFoodProduct(FoodProductRequestDTO foodProductRequestDTO) {
        FoodProduct foodProduct = foodProductMapper.mapToEntity(foodProductRequestDTO);
        FoodProduct savedFoodProduct = foodProductRepository.save(foodProduct);
        return foodProductMapper.mapToResponseDto(savedFoodProduct);
    }

    public FoodProductResponseDTO updateFoodProduct(Long id, FoodProductRequestDTO foodProductRequestDTO) {
        FoodProduct existingFoodProduct = foodProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodProduct Not Found"));

        // Update existingFoodProduct with data from foodProductRequestDTO
        existingFoodProduct.setName(foodProductRequestDTO.getName());

        // Update Nutrition
        Nutrition nutrition = existingFoodProduct.getNutrition();
        nutrition.getProtein().setTotalProtein(foodProductRequestDTO.getNutrition().getProtein().getTotalProtein());
        nutrition.getFat().setTotalFat(foodProductRequestDTO.getNutrition().getFat().getTotalFat());
        nutrition.getFat().setTransFat(foodProductRequestDTO.getNutrition().getFat().getTransFat());
        nutrition.getCarbs().setTotalCarbs(foodProductRequestDTO.getNutrition().getCarbs().getTotalCarbs());
        nutrition.getCarbs().setSugar(foodProductRequestDTO.getNutrition().getCarbs().getSugar());

        // Update DietaryRestrictions
        DietaryRestrictions dietaryRestrictions = existingFoodProduct.getDietaryRestrictions();
        dietaryRestrictions.setGlutenFree(foodProductRequestDTO.getDietaryRestrictions().isGlutenFree());
        dietaryRestrictions.setLactoseFree(foodProductRequestDTO.getDietaryRestrictions().isLactoseFree());
        dietaryRestrictions.setVegan(foodProductRequestDTO.getDietaryRestrictions().isVegan());
        dietaryRestrictions.setVegetarian(foodProductRequestDTO.getDietaryRestrictions().isVegetarian());

        // Save the updated food product
        FoodProduct updatedFoodProduct = foodProductRepository.save(existingFoodProduct);
        return foodProductMapper.mapToResponseDto(updatedFoodProduct);
    }
    public boolean deleteFoodProduct(Long id) {
        FoodProduct foodProduct = foodProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodProduct Not Found"));

        foodProductRepository.delete(foodProduct);

        // Check if the food product is still present in the repository after deletion
        return !foodProductRepository.existsById(id);
    }


}
