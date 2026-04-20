package com.example.ProductPrice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProductPrice.model.ProductPrice;
import com.example.ProductPrice.service.ProductPriceService;


@RestController
@RequestMapping("/prices")
public class ProductPriceController {

    @Autowired
    private ProductPriceService service;

    @GetMapping("/{id}")
    public ProductPrice getPrice(@PathVariable int id) {
        return service.getProductPrice(id);
    }
}
