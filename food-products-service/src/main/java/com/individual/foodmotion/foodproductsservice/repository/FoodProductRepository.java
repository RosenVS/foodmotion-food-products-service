package com.individual.foodmotion.foodproductsservice.repository;

import com.individual.foodmotion.foodproductsservice.model.FoodProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodProductRepository extends JpaRepository<FoodProduct, Long> {
}
