package com.example.ProductInventory.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProductInventory.model.ProductInventory;
import com.example.ProductInventory.repo.IProductInventoryRepository;


@Service
public class ProductInventoryService {
	
	@Autowired
	IProductInventoryRepository iProductInventoryRepository;
	
	public ProductInventory getProductStock(int id) {

		Optional<ProductInventory> op=iProductInventoryRepository.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}
		
		return null;
		
		
	}

}
