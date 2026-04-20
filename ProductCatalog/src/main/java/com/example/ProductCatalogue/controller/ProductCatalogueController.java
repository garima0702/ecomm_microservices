package com.example.ProductCatalogue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.ProductCatalogue.model.ProductCatalogDTO;
import com.example.ProductCatalogue.service.ProductCatalogueService;

@RestController

@RequestMapping("/catalogs")
public class ProductCatalogueController {

    @Autowired
    private ProductCatalogueService service;

    // ✅ Get all products
    @GetMapping("/products")
    public List<ProductCatalogDTO> getAllProducts() {
        return service.getAllProducts();
    }

    // ✅ Get by ID
    @GetMapping("/products/{id}")
    public ProductCatalogDTO getProduct(@PathVariable int id) {
        return service.getProductById(id);
    }

    // ✅ Get by category
    @GetMapping("/products/category/{category}")
    public List<ProductCatalogDTO> getByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }
}