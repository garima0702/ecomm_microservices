package com.example.ProductCatalogue.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ProductCatalogue.model.Product;
import com.example.ProductCatalogue.model.ProductCatalogDTO;
import com.example.ProductCatalogue.model.ProductInventory;
import com.example.ProductCatalogue.model.ProductPrice;

@Service


public class ProductCatalogueService {

    @Autowired
    private RestTemplate restTemplate;

    private final String PRODUCT_URL = "http://Product/products";
    private final String PRICE_URL = "http://ProductPrice/prices";
    private final String INVENTORY_URL = "http://ProductStock/inventory";

    // ✅ Get product by ID
    public ProductCatalogDTO getProductById(int pid) {

        Product product = restTemplate.getForObject(PRODUCT_URL+"/" + pid, Product.class);
        ProductPrice price = restTemplate.getForObject(PRICE_URL+"/" + pid, ProductPrice.class);
        ProductInventory inventory = restTemplate.getForObject(INVENTORY_URL+"/" + pid, ProductInventory.class);

        ProductCatalogDTO dto = new ProductCatalogDTO();

        dto.setPid(pid);
        dto.setPname(product.getPname());
        dto.setPcategory(product.getPcategory());
        dto.setDiscountedPrice(price.getPrice() - price.getDiscount());
        dto.setNoOfItems(inventory.getNoOfItemsLeft());

        return dto;
    }

    // ✅ Get all products
    public List<ProductCatalogDTO> getAllProducts() {

        Product[] products = restTemplate.getForObject(PRODUCT_URL, Product[].class);

        List<ProductCatalogDTO> list = new ArrayList<>();

        for (Product p : products) {

            ProductPrice price = restTemplate.getForObject(PRICE_URL+"/" + p.getPid(), ProductPrice.class);
            ProductInventory inventory = restTemplate.getForObject(INVENTORY_URL+"/" + p.getPid(), ProductInventory.class);

            ProductCatalogDTO dto = new ProductCatalogDTO();
            dto.setPid(p.getPid());
            dto.setPname(p.getPname());
            dto.setPcategory(p.getPcategory());
            dto.setDiscountedPrice(price.getPrice() - price.getDiscount());
            dto.setNoOfItems(inventory.getNoOfItemsLeft());

            list.add(dto);
        }

        return list;
    }

    // ✅ Get by category
    public List<ProductCatalogDTO> getByCategory(String category) {

        Product[] products = restTemplate.getForObject(
                PRODUCT_URL+"/" + "category/" + category,
                Product[].class
        );

        List<ProductCatalogDTO> list = new ArrayList<>();

        for (Product p : products) {

            ProductPrice price = restTemplate.getForObject(PRICE_URL+"/" + p.getPid(), ProductPrice.class);
            ProductInventory inventory = restTemplate.getForObject(INVENTORY_URL +"/"+ p.getPid(), ProductInventory.class);

            ProductCatalogDTO dto = new ProductCatalogDTO();
            dto.setPid(p.getPid());
            dto.setPname(p.getPname());
            dto.setPcategory(p.getPcategory());
            dto.setDiscountedPrice(price.getPrice() - price.getDiscount());
            dto.setNoOfItems(inventory.getNoOfItemsLeft());

            list.add(dto);
        }

        return list;
    }
}
