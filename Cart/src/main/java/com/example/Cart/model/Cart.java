package com.example.Cart.model;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cart {

    @Id
    private int cartId;

    // getters & setters
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
}