
package com.viola.coffeDelivery.model;


public class Product {
    private int  productID;
    private String name ;
    private double quantity ;
    private double price;
    private int categoryID;

    public Product() {
    }

    
 
    public Product(int productID, String name, double quantity, double price, int categoryID) {
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.categoryID = categoryID;
    }
    

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    
    
    
}
