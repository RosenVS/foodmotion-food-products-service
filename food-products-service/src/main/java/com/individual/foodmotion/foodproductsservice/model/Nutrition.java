package com.individual.foodmotion.foodproductsservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_product_id")
    private FoodProduct foodProduct;

    @OneToOne(mappedBy = "nutrition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Protein protein;

    @OneToOne(mappedBy = "nutrition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Fat fat;

    @OneToOne(mappedBy = "nutrition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Carbs carbs;
}
