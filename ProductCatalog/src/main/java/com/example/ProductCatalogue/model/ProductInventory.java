package com.example.ProductCatalogue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProductInventory {
	@Id
	private int pid;
	private int noOfItemsLeft;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getNoOfItemsLeft() {
		return noOfItemsLeft;
	}
	public void setNoOfItemsLeft(int noOfItemsLeft) {
		this.noOfItemsLeft = noOfItemsLeft;
	}
	
	
	
	

}
