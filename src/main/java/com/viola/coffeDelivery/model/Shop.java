package com.viola.coffeDelivery.model;


public class Shop {
    
    private int shopID ;
    private String name ;
    private double lat;
    private double lng;
    private int  productID;

    public Shop() {
    }
    
    

    public Shop(int shopID, String name, double lat, double lng , int productID) {
        this.shopID = shopID;
        this.name = name;
        this.lat = lat;
        this.lng = lng ;
        this.productID = productID;
    }

    

    public int getId() {
        return shopID;
    }

    public void setId(int id) {
        this.shopID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongtitude() {
        return lng;
    }

    public void setLongtitude(float lng) {
        this.lng = lng;
    }

    public double getLatitude() {
        return lat;
    }

    public void setLatitude(float lat) {
        this.lat = lat;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
     
   
    
    
}
