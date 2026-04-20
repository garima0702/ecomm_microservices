	package com.example.Cart.service;
	
	import java.util.*;
	
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
	import org.springframework.web.client.RestTemplate;
	
	import com.example.Cart.model.*;
	import com.example.Cart.repo.*;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
	
	@Service
	public class CartService {
	
	    @Autowired
	    private CartRepository cartRepo;
	
	    @Autowired
	    private CartItemRepository cartItemRepo;
	
	    @Autowired
	    private RestTemplate restTemplate;
	
	    private final String CATALOG_URL = "http://localhost:8050/catalogs/products/";
	    //private final String RECOMMENDATION_URL = "http://localhost:8060/recommendations";
	
	    // ✅ Add to cart
	    public String addToCart(int cartId, int pid) {
	
	        // create cart if not exists
	        if (!cartRepo.existsById(cartId)) {
	            Cart cart = new Cart();
	            cart.setCartId(cartId);
	            cartRepo.save(cart);
	        }
	
	        // save cart item
	        CartItem item = new CartItem();
	        item.setCartId(cartId);
	        item.setPid(pid);
	
	        cartItemRepo.save(item);
	
	        return "Product added to cart";
	    }
	
	    // ✅ Get cart
	    @CircuitBreaker(name = "catalogService", fallbackMethod = "getCartFallback")
	    public Map<String, Object> getCart(int cartId) {
	
	        List<CartItem> items = cartItemRepo.findByCartId(cartId);
	
	        List<ProductCatalogDTO> products = new ArrayList<>();
	        List<Integer> pids = new ArrayList<>();
	
	        for (CartItem item : items) {
	
	            int pid = item.getPid();
	            pids.add(pid);
	
	            ProductCatalogDTO product =
	                    restTemplate.getForObject(CATALOG_URL + pid, ProductCatalogDTO.class);
	
	            products.add(product);
	        }

	        Map<String, Object> response = new HashMap<>();
	        response.put("products", products);
	  
	
	        return response;
	    }
	    
	    
	    // ✅ Fallback method
	    public Map<String, Object> getCartFallback(int cartId, Throwable t) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "Catalog service is currently unavailable");
	        response.put("products", new ArrayList<>()); // return empty product list
	        response.put("error", t.getMessage());
	        return response;
	    }
	    
	    

	    
	    public List<ProductCatalogDTO> getRecommendations(int cartId) {
	
	        List<CartItem> items = cartItemRepo.findByCartId(cartId);
	
	        if (items == null || items.isEmpty()) {
	            throw new RuntimeException("Cart is empty");
	        }
	
	        List<String> categories = new ArrayList<>();
	
	        for (CartItem item : items) {
	
	            int pid = item.getPid();
	
	            // fetch full product
	            ProductCatalogDTO product =
	                    restTemplate.getForObject(CATALOG_URL + pid, ProductCatalogDTO.class);
	
	            if (product != null) {
	                categories.add(product.getPcategory());
	            }
	        }
	
	        // remove duplicates
	        categories = new ArrayList<>(new HashSet<>(categories));
	
	        // 🔥 call recommendation service
	        ResponseEntity<List<ProductCatalogDTO>> response =
	                restTemplate.exchange(
	                        "http://localhost:8056/recommendations/category",
	                        HttpMethod.POST,
	                        new HttpEntity<>(categories),
	                        new ParameterizedTypeReference<List<ProductCatalogDTO>>() {}
	                );

	        return response.getBody();
	    }
	}