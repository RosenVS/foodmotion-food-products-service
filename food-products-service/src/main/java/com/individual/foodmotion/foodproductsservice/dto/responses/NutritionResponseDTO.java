package com.individual.foodmotion.foodproductsservice.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NutritionResponseDTO {
    private ProteinResponseDTO protein;
    private FatResponseDTO fat;
    private CarbsResponseDTO carbs;
}
