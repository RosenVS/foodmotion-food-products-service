package com.individual.foodmotion.foodproductsservice.dto.requests;

import com.individual.foodmotion.foodproductsservice.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatusUpdateRequest {
    private Long recipe_id;
    private Status status;
}