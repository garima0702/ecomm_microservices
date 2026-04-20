package com.example.ProductInventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProductInventory.model.ProductInventory;
import com.example.ProductInventory.service.ProductInventoryService;



@RestController
@RequestMapping("/inventory")
public class ProductInventoryController {

    @Autowired
    private ProductInventoryService service;

    @GetMapping("/{id}")
    public ProductInventory getStock(@PathVariable int id) {
        return service.getProductStock(id);
    }
}


