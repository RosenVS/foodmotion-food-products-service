package com.individual.foodmotion.foodproductsservice.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper=true)
@Entity
public class DietaryRestrictions  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_product_id")
    private FoodProduct foodProduct;

    private boolean glutenFree;
    private boolean lactoseFree;
    private boolean vegan;
    private boolean vegetarian;
}
