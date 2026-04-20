package com.example.ProductPrice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProductPrice.model.ProductPrice;

@Repository
public interface IProductPriceRepository extends JpaRepository<ProductPrice, Integer> {

}
