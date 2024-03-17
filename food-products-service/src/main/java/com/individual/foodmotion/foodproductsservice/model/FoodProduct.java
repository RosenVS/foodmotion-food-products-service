package com.individual.foodmotion.foodproductsservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FoodProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(mappedBy = "foodProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Nutrition nutrition;

    @OneToOne(mappedBy = "foodProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DietaryRestrictions dietaryRestrictions;

}