package com.individual.foodmotion.foodproductsservice.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarbsResponseDTO {
    private double totalCarbs;
    private double sugar;
}
