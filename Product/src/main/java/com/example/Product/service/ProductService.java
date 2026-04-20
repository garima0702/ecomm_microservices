package com.example.Product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Product.model.Product;
import com.example.Product.repo.IProductRepository;

@Service
public class ProductService {
	
	@Autowired
	IProductRepository iProductRepository;
	
	public List<Product> getAllProducts(){
		 List<Product> l= iProductRepository.findAll();
		 return l;
		
		
	}
	
	public Product getProductById(int id) {
	    Optional<Product> op = iProductRepository.findById(id);

	    if (op.isPresent()) {
	        return op.get();
	    }

	    return null;
	}
	
	public List<Product> getByCategory(String category) {
	    return iProductRepository.findByPcategory(category);
	}
	
	

}
