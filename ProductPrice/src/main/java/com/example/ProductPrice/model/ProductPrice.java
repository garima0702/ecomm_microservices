package com.example.ProductPrice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProductPrice {
	@Id
	private int pid;
	private double price;
	private double discount;
	
	
	
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getId() {
		return pid;
	}
	public void setId(int id) {
		this.pid = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
	

}
