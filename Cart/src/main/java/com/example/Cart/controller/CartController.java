package com.example.Cart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Cart.model.ProductCatalogDTO;
import com.example.Cart.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService service;

    // ✅ Add product (only pid)
    @PostMapping("/{cartId}/{pid}")
    public String addToCart(@PathVariable int cartId, @PathVariable int pid) {
        return service.addToCart(cartId, pid);
    }

    // ✅ Get cart
    @GetMapping("/{cartId}")
    public Map<String, Object> getCart(@PathVariable int cartId) {
        return service.getCart(cartId);
    }
    
    @GetMapping("/{cartId}/recommendations")
    public List<ProductCatalogDTO> getRecommendations(@PathVariable int cartId) {
        return service.getRecommendations(cartId);
    }
}