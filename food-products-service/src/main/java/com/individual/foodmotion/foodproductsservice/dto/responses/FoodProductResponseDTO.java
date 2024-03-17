package com.individual.foodmotion.foodproductsservice.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodProductResponseDTO {
    private Long id;
    private String name;
    private NutritionResponseDTO nutrition;
    private DietaryRestrictionsResponseDTO dietaryRestrictions;
}
