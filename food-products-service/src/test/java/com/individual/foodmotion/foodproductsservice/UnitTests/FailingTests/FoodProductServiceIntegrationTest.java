package com.individual.foodmotion.foodproductsservice.UnitTests.FailingTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.individual.foodmotion.foodproductsservice.controller.FoodProductsController;
import com.individual.foodmotion.foodproductsservice.dto.requests.FoodProductRequestDTO;
import com.individual.foodmotion.foodproductsservice.exception.ResourceNotFoundException;
import com.individual.foodmotion.foodproductsservice.service.FoodProductService;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FoodProductServiceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodProductService foodProductService;

    @InjectMocks
    private FoodProductsController foodProductsController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testCreateFoodProduct_NotFound() throws Exception {
        // Arrange
        long id = 2L;
        FoodProductRequestDTO requestDTO = new FoodProductRequestDTO();
        requestDTO.setName("Sample Food Product");
        requestDTO.setNutrition(null); // Set nutrition to null for simplicity
        requestDTO.setDietaryRestrictions(null); // Set dietary restrictions to null for simplicity

        // Mock the service to throw ResourceNotFoundException when createFoodProduct is called
        when(foodProductService.createFoodProduct(any(FoodProductRequestDTO.class)))
                .thenThrow(new ResourceNotFoundException("Food product not found"));

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        // Act & Assert
        mockMvc.perform(post("/api/food-product")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }
    @Test
    void testGetFoodProductById_NotFound() throws Exception {
        // Arrange
        long id = 2L;

        // Mock the service to throw ResourceNotFoundException when getFoodProductById is called
        when(foodProductService.getFoodProductById(id))
                .thenThrow(new ResourceNotFoundException("Food product not found"));

        // Act & Assert
        mockMvc.perform(get("/api/food-product/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }
    @Test
    void testDeleteFoodProduct_False() throws Exception {
        // Arrange
        long id = 2L;

        // Mock the service to return false when deleteFoodProduct is called
        when(foodProductService.deleteFoodProduct(id)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(delete("/api/food-product/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    void testUpdateFoodProduct_NotFound() throws Exception {
        // Arrange
        long id = 2L;
        FoodProductRequestDTO requestDTO = new FoodProductRequestDTO();
        requestDTO.setName("Sample Food Product");
        requestDTO.setNutrition(null); // Set nutrition to null to simulate the case where the food product is not found
        requestDTO.setDietaryRestrictions(null); // Set dietary restrictions to null for simplicity

        // Mock the service to throw ResourceNotFoundException when updateFoodProduct is called
        when(foodProductService.updateFoodProduct(eq(id), any(FoodProductRequestDTO.class)))
                .thenThrow(new ResourceNotFoundException("Food product not found"));

        String requestBody = objectMapper.writeValueAsString(requestDTO);

        // Act & Assert
        mockMvc.perform(put("/api/food-product/{id}", id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }
}
