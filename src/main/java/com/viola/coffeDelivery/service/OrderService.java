package com.viola.coffeDelivery.service;

import com.viola.coffeDelivery.model.Order;
import java.util.List;

public interface OrderService {
    
    List<Order> listAllOrders();
    
    Order findOrderById(int orderID);
    
    void saveOrder(Order order);
    
    void deleteOrderById (int orderID);
    
    void updateOrder(Order order);
    
    void deleteAllOrders();
    
    boolean isOrderExist(Order order);
    
    
}
