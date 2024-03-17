package com.individual.foodmotion.foodproductsservice.UnitTests.SuccessfulTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.individual.foodmotion.foodproductsservice.controller.FoodProductsController;
import com.individual.foodmotion.foodproductsservice.dto.requests.*;
import com.individual.foodmotion.foodproductsservice.dto.responses.*;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
    void testCreateFoodProduct() throws Exception {
        // Arrange
        FoodProductRequestDTO requestDTO = new FoodProductRequestDTO();
        requestDTO.setName("Sample Food Product");
        requestDTO.setNutrition(new NutritionRequestDTO());
        requestDTO.getNutrition().setProtein(new ProteinRequestDTO(20.1));
        requestDTO.getNutrition().setFat(new FatRequestDTO(20.2, 20.3));
        requestDTO.getNutrition().setCarbs(new CarbsRequestDTO(20.4, 20.5));
        requestDTO.setDietaryRestrictions(new DietaryRestrictionsRequestDTO(true, true, true, true));

        // Mock the service to return a successful response
        FoodProductResponseDTO expectedResponse = new FoodProductResponseDTO();
        expectedResponse.setId(6L);
        expectedResponse.setName(requestDTO.getName());
        expectedResponse.setNutrition(new NutritionResponseDTO(
                new ProteinResponseDTO(requestDTO.getNutrition().getProtein().getTotalProtein()),
                new FatResponseDTO(requestDTO.getNutrition().getFat().getTotalFat(), requestDTO.getNutrition().getFat().getTransFat()),
                new CarbsResponseDTO(requestDTO.getNutrition().getCarbs().getTotalCarbs(), requestDTO.getNutrition().getCarbs().getSugar())
        ));
        expectedResponse.setDietaryRestrictions(new DietaryRestrictionsResponseDTO(true, true, true, true));

        when(foodProductService.createFoodProduct(any(FoodProductRequestDTO.class))).thenReturn(expectedResponse);

        String requestBody = objectMapper.writeValueAsString(requestDTO);
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/food-product")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void testUpdateFoodProduct() throws Exception {
        // Arrange
        long id = 2L;
        FoodProductRequestDTO requestDTO = new FoodProductRequestDTO();
        requestDTO.setName("Sample Food Product");
        requestDTO.setNutrition(new NutritionRequestDTO());
        requestDTO.getNutrition().setProtein(new ProteinRequestDTO(20.9));
        requestDTO.getNutrition().setFat(new FatRequestDTO(20.2, 20.3));
        requestDTO.getNutrition().setCarbs(new CarbsRequestDTO(20.4, 20.5));
        requestDTO.setDietaryRestrictions(new DietaryRestrictionsRequestDTO(true, true, true, true));

        // Mock the service to return a successful response
        FoodProductResponseDTO expectedResponse = new FoodProductResponseDTO();
        expectedResponse.setId(id);
        expectedResponse.setName(requestDTO.getName());
        expectedResponse.setNutrition(new NutritionResponseDTO(
                new ProteinResponseDTO(requestDTO.getNutrition().getProtein().getTotalProtein()),
                new FatResponseDTO(requestDTO.getNutrition().getFat().getTotalFat(), requestDTO.getNutrition().getFat().getTransFat()),
                new CarbsResponseDTO(requestDTO.getNutrition().getCarbs().getTotalCarbs(), requestDTO.getNutrition().getCarbs().getSugar())
        ));
        expectedResponse.setDietaryRestrictions(new DietaryRestrictionsResponseDTO(true, true, true, true));

        when(foodProductService.updateFoodProduct(eq(id), any(FoodProductRequestDTO.class))).thenReturn(expectedResponse);

        String requestBody = objectMapper.writeValueAsString(requestDTO);
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/api/food-product/{id}", id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void testDeleteFoodProduct() throws Exception {
        // Arrange
        long id = 2L;

        // Mock the service to return a successful deletion response
        when(foodProductService.deleteFoodProduct(id)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/food-product/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("true"));
    }
    @Test
    void testGetAllFoodProducts() throws Exception {
        // Arrange
        FoodProductResponseDTO foodProduct1 = new FoodProductResponseDTO();
        foodProduct1.setId(1L);
        foodProduct1.setName("Food Product 1");

        FoodProductResponseDTO foodProduct2 = new FoodProductResponseDTO();
        foodProduct2.setId(2L);
        foodProduct2.setName("Food Product 2");

        List<FoodProductResponseDTO> expectedResponse = Arrays.asList(foodProduct1, foodProduct2);

        // Mock the service to return a list of food products
        when(foodProductService.getAllFoodProducts()).thenReturn(expectedResponse);

        String expectedJson = objectMapper.writeValueAsString(expectedResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/food-product"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void testGetFoodProductById() throws Exception {
        // Arrange
        long id = 2L;

        FoodProductResponseDTO expectedResponse = new FoodProductResponseDTO();
        expectedResponse.setId(id);
        expectedResponse.setName("Food Product");

        // Mock the service to return the food product by ID
        when(foodProductService.getFoodProductById(id)).thenReturn(expectedResponse);

        String expectedJson = objectMapper.writeValueAsString(expectedResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/food-product/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(expectedJson));
    }

}
