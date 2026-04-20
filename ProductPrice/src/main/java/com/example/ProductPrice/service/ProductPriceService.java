package com.example.ProductPrice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProductPrice.model.ProductPrice;
import com.example.ProductPrice.repo.IProductPriceRepository;

@Service
public class ProductPriceService {
	
	@Autowired
	IProductPriceRepository iProductPriceRepository;
	
	public ProductPrice getProductPrice(int id) {

		Optional<ProductPrice> op=iProductPriceRepository.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}
		
		return null;
		
		
	}

}
