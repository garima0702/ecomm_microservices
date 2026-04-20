package com.example.ProductCatalogue.model;

public class ProductCatalogDTO {


    private int pid;
    private String pname;
    private String pcategory;
    private double discountedPrice;
    private int noOfItems;

    // getters & setters
    public int getPid() { return pid; }
    public void setPid(int pid) { this.pid = pid; }

    public String getPname() { return pname; }
    public void setPname(String pname) { this.pname = pname; }

    public String getPcategory() { return pcategory; }
    public void setPcategory(String pcategory) { this.pcategory = pcategory; }

    public double getDiscountedPrice() { return discountedPrice; }
    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public int getNoOfItems() { return noOfItems; }
    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }
}
