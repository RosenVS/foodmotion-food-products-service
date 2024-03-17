package com.individual.foodmotion.foodproductsservice.UnitTests.SuccessfulTests;

import com.individual.foodmotion.foodproductsservice.controller.FoodProductsController;
import com.individual.foodmotion.foodproductsservice.dto.requests.FoodProductRequestDTO;
import com.individual.foodmotion.foodproductsservice.dto.responses.FoodProductResponseDTO;
import com.individual.foodmotion.foodproductsservice.service.FoodProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FoodProductServiceTestPassingTest {
    @Mock
    private FoodProductService foodProductService;

    @InjectMocks
    private FoodProductsController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetHello() {
        ResponseEntity<String> response = controller.getHello();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("hello world", response.getBody());
    }

    @Test
    void testGetAllFoodProducts() {
        List<FoodProductResponseDTO> expectedFoodProducts = new ArrayList<>();
        when(foodProductService.getAllFoodProducts()).thenReturn(expectedFoodProducts);

        ResponseEntity<List<FoodProductResponseDTO>> response = controller.getAllFoodProducts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedFoodProducts, response.getBody());
    }

    @Test
    void testGetFoodProductById() {
        long id = 1L;
        FoodProductResponseDTO expectedFoodProduct = new FoodProductResponseDTO();
        when(foodProductService.getFoodProductById(id)).thenReturn(expectedFoodProduct);

        ResponseEntity<FoodProductResponseDTO> response = controller.getFoodProductById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedFoodProduct, response.getBody());
    }

    @Test
    void testCreateFoodProduct() {
        FoodProductRequestDTO requestDTO = new FoodProductRequestDTO();
        FoodProductResponseDTO expectedFoodProduct = new FoodProductResponseDTO();
        when(foodProductService.createFoodProduct(requestDTO)).thenReturn(expectedFoodProduct);

        ResponseEntity<FoodProductResponseDTO> response = controller.createFoodProduct(requestDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedFoodProduct, response.getBody());
    }

    @Test
    void testUpdateFoodProduct() {
        long id = 1L;
        FoodProductRequestDTO requestDTO = new FoodProductRequestDTO();
        FoodProductResponseDTO expectedFoodProduct = new FoodProductResponseDTO();
        when(foodProductService.updateFoodProduct(id, requestDTO)).thenReturn(expectedFoodProduct);

        ResponseEntity<FoodProductResponseDTO> response = controller.updateFoodProduct(id, requestDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedFoodProduct, response.getBody());
    }

    @Test
    void testDeleteFoodProduct() {
        long id = 1L;
        when(foodProductService.deleteFoodProduct(id)).thenReturn(true);

        ResponseEntity<Boolean> response = controller.deleteFoodProduct(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }
}
