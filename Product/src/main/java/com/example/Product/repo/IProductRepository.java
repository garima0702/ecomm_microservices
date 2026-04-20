package com.example.Product.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Product.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByPcategory(String pcategory);

}
