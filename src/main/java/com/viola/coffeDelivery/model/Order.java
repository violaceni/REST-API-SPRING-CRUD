package com.viola.coffeDelivery.model;

import java.util.Date;


public class Order {
    private int orderID;
    private Date dataTime;
    private int userID;

    public Order() {
    }
    
    

    public Order(int orderID, Date dataTime, int userID) {
        this.orderID = orderID;
        this.dataTime = dataTime;
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

   
    
    
    
}
