package com.individual.foodmotion.foodproductsservice.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NutritionRequestDTO {
    private ProteinRequestDTO protein;
    private FatRequestDTO fat;
    private CarbsRequestDTO carbs;
}
