package com.example.Cart.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Cart.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> findByCartId(int cartId);
}
