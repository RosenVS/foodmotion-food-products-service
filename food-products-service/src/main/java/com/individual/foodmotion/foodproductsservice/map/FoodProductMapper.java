package com.individual.foodmotion.foodproductsservice.map;

import com.individual.foodmotion.foodproductsservice.dto.requests.FoodProductRequestDTO;
import com.individual.foodmotion.foodproductsservice.dto.responses.*;
import com.individual.foodmotion.foodproductsservice.model.*;
import org.springframework.stereotype.Component;

@Component
public class FoodProductMapper {
    public FoodProduct mapToEntity(FoodProductRequestDTO requestDTO) {
        FoodProduct entity = new FoodProduct();
        entity.setName(requestDTO.getName());

        // Map Nutrition
        Nutrition nutrition = new Nutrition();
        entity.setNutrition(nutrition);
        nutrition.setFoodProduct(entity);

        // Map Protein
        Protein protein = new Protein();
        nutrition.setProtein(protein);
        protein.setNutrition(nutrition);
        protein.setTotalProtein(requestDTO.getNutrition().getProtein().getTotalProtein());

        // Map Fat
        Fat fat = new Fat();
        nutrition.setFat(fat);
        fat.setNutrition(nutrition);
        fat.setTotalFat(requestDTO.getNutrition().getFat().getTotalFat());
        fat.setTransFat(requestDTO.getNutrition().getFat().getTransFat());

        // Map Carbs
        Carbs carbs = new Carbs();
        nutrition.setCarbs(carbs);
        carbs.setNutrition(nutrition);
        carbs.setTotalCarbs(requestDTO.getNutrition().getCarbs().getTotalCarbs());
        carbs.setSugar(requestDTO.getNutrition().getCarbs().getSugar());

        // Map DietaryRestrictions
        DietaryRestrictions dietaryRestrictions = new DietaryRestrictions();
        entity.setDietaryRestrictions(dietaryRestrictions);
        dietaryRestrictions.setFoodProduct(entity);
        dietaryRestrictions.setGlutenFree(requestDTO.getDietaryRestrictions().isGlutenFree());
        dietaryRestrictions.setLactoseFree(requestDTO.getDietaryRestrictions().isLactoseFree());
        dietaryRestrictions.setVegan(requestDTO.getDietaryRestrictions().isVegan());
        dietaryRestrictions.setVegetarian(requestDTO.getDietaryRestrictions().isVegetarian());

        return entity;
    }

    public FoodProductResponseDTO mapToResponseDto(FoodProduct entity) {
        FoodProductResponseDTO responseDTO = new FoodProductResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setName(entity.getName());
        responseDTO.setNutrition(mapNutritionToResponseDto(entity.getNutrition()));
        responseDTO.setDietaryRestrictions(mapDietaryRestrictionsToResponseDto(entity.getDietaryRestrictions()));
        // Map other properties as needed
        return responseDTO;
    }

    private NutritionResponseDTO mapNutritionToResponseDto(Nutrition nutrition) {
        NutritionResponseDTO nutritionResponseDTO = new NutritionResponseDTO();
        nutritionResponseDTO.setProtein(ProteinResponseDTO.builder()
                .totalProtein(nutrition.getProtein().getTotalProtein())
                .build());
        nutritionResponseDTO.setFat(FatResponseDTO.builder()
                .totalFat(nutrition.getFat().getTotalFat())
                .transFat(nutrition.getFat().getTransFat())
                .build());
        nutritionResponseDTO.setCarbs(CarbsResponseDTO.builder()
                .totalCarbs(nutrition.getCarbs().getTotalCarbs())
                .sugar(nutrition.getCarbs().getSugar())
                .build());
        return nutritionResponseDTO;
    }

    private DietaryRestrictionsResponseDTO mapDietaryRestrictionsToResponseDto(DietaryRestrictions dietaryRestrictions) {
        return DietaryRestrictionsResponseDTO.builder()
                .glutenFree(dietaryRestrictions.isGlutenFree())
                .lactoseFree(dietaryRestrictions.isLactoseFree())
                .vegan(dietaryRestrictions.isVegan())
                .vegetarian(dietaryRestrictions.isVegetarian())
                .build();
    }
//public FoodProduct mapToEntity(FoodProductRequestDTO requestDTO) {
//    FoodProduct entity = new FoodProduct();
//    entity.setName(requestDTO.getName());
//
//    // Initialize Nutrition
//    Nutrition nutrition = new Nutrition();
//    nutrition.setFoodProduct(entity);
//
//    // Initialize Protein
//    Protein protein = new Protein();
//    protein.setNutrition(nutrition);
//
//    // Initialize Fat
//    Fat fat = new Fat();
//    fat.setNutrition(nutrition);
//
//    // Initialize Carbs
//    Carbs carbs = new Carbs();
//    carbs.setNutrition(nutrition);
//
//    // Initialize DietaryRestrictions
//    DietaryRestrictions dietaryRestrictions = new DietaryRestrictions();
//    dietaryRestrictions.setFoodProduct(entity);
//
//    // Set entities
//    entity.setNutrition(nutrition);
//    entity.setDietaryRestrictions(dietaryRestrictions);
//
//    // Set Nutrition properties
//    nutrition.setProtein(protein);
//    nutrition.setFat(fat);
//    nutrition.setCarbs(carbs);
//
//    // Set DietaryRestrictions properties
//    dietaryRestrictions.setGlutenFree(requestDTO.getDietaryRestrictions().isGlutenFree());
//    dietaryRestrictions.setLactoseFree(requestDTO.getDietaryRestrictions().isLactoseFree());
//    dietaryRestrictions.setVegan(requestDTO.getDietaryRestrictions().isVegan());
//    dietaryRestrictions.setVegetarian(requestDTO.getDietaryRestrictions().isVegetarian());
//
//    // Set Protein properties
//    protein.setProtein(requestDTO.getNutrition().getProtein().getProtein());
//
//    // Set Fat properties
//    fat.setTotalFat(requestDTO.getNutrition().getFat().getTotalFat());
//    fat.setTransFat(requestDTO.getNutrition().getFat().getTransFat());
//
//    // Set Carbs properties
//    carbs.setTotalCarbs(requestDTO.getNutrition().getCarbs().getTotalCarbs());
//    carbs.setSugar(requestDTO.getNutrition().getCarbs().getSugar());
//
//    // Map other properties as needed
//    return entity;
//}
//
//    public FoodProductResponseDTO mapToResponseDto(FoodProduct entity) {
//        FoodProductResponseDTO responseDTO = new FoodProductResponseDTO();
//        responseDTO.setId(entity.getId());
//        responseDTO.setName(entity.getName());
//
//        // Map Nutrition properties
//        Nutrition nutrition = entity.getNutrition();
//        if (nutrition != null) {
//            responseDTO.setNutrition(NutritionResponseDTO.builder()
//                    .protein(ProteinResponseDTO.builder()
//                            .totalProtein(nutrition.getProtein().getProtein())
//                            .build())
//                    .fat(FatResponseDTO.builder()
//                            .totalFat(nutrition.getFat().getTotalFat())
//                            .transFat(nutrition.getFat().getTransFat())
//                            .build())
//                    .carbs(CarbsResponseDTO.builder()
//                            .totalCarbs(nutrition.getCarbs().getTotalCarbs())
//                            .sugar(nutrition.getCarbs().getSugar())
//                            .build())
//                    .build());
//        }
//
//        // Map DietaryRestrictions properties
//        DietaryRestrictions dietaryRestrictions = entity.getDietaryRestrictions();
//        if (dietaryRestrictions != null) {
//            responseDTO.setDietaryRestrictions(DietaryRestrictionsResponseDTO.builder()
//                    .glutenFree(dietaryRestrictions.isGlutenFree())
//                    .lactoseFree(dietaryRestrictions.isLactoseFree())
//                    .vegan(dietaryRestrictions.isVegan())
//                    .vegetarian(dietaryRestrictions.isVegetarian())
//                    .build());
//        }
//
//        // Map other properties as needed
//        return responseDTO;
//    }
}
