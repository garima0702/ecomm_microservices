package com.example.Recommendation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Recommendation.model.ProductCatalogDTO;
import com.example.Recommendation.service.RecommendationService;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService service;

    // 🔥 NEW: category-based recommendation
    @PostMapping("/category")
    public List<ProductCatalogDTO> getByCategory(@RequestBody List<String> categories) {
        return service.getByCategory(categories);
    }
}