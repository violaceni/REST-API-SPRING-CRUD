package com.viola.coffeDelivery.service;

import com.viola.coffeDelivery.model.Order;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImp implements OrderService {

    private static final AtomicLong counter = new AtomicLong();
    
    private static List<Order> orders = new ArrayList<>();

    static {
        try {
            orders = populateOrder();
        } catch (ParseException ex) {
            Logger.getLogger(OrderServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Order> listAllOrders() {
        return orders;
    }
    
    @Override
    public Order findOrderById(int orderID){
        for(Order order:orders){
        if(order.getOrderID()== orderID)
         return order;
        }
    return null;
    }
    
    @Override
    public void saveOrder(Order order){
    order.setOrderID((int) counter.incrementAndGet());
    orders.add(order);
    }
    
    public void updateOrder(Order order){
    int index = orders.indexOf(order);
     orders.set(index,order);
     }
    
    @Override
    public void deleteAllOrders(){
    orders.clear();
    }
    
    public  void deleteOrderById(int orderID){
    for(Iterator<Order> iterator =orders.iterator();iterator.hasNext(); ){
     Order order = iterator.next();
     if(order.getOrderID()==orderID)
         iterator.remove();
    }
       
    }

    private static List<Order> populateOrder() throws ParseException {
        String[] string1 = {"2018-10-09 2:10:21", "2018-11-09 1:15:21", "2018-10-19 1:16:21", "2018-1-09 1:12:21", "2018-10-09 1:17:21"};
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS", Locale.ENGLISH);
        for (int i = 0; i < string1.length; i++) {

            Date date = format.parse(string1[i]);

            orders.add(new Order((int) counter.incrementAndGet(), date, i + 1));

        }

        return orders;
    }
    
    @Override
    public boolean isOrderExist(Order order){
   
    return findOrderById(order.getOrderID())!= null;
    
    }
}
