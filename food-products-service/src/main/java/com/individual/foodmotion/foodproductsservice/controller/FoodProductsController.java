package com.individual.foodmotion.foodproductsservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class FoodProductsController {

    @GetMapping
    public ResponseEntity<String> getHello() {
        // Change the return value to "hello world"
        return ResponseEntity.ok("hello world");
    }


    // generate
    // getQuestions (questionid)
    // getScore

}
