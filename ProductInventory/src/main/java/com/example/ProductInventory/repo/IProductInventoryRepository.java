package com.example.ProductInventory.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProductInventory.model.ProductInventory;

@Repository
public interface IProductInventoryRepository extends JpaRepository<ProductInventory, Integer> {

}
