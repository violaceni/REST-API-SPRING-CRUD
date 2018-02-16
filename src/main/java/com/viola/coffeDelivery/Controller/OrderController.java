package com.viola.coffeDelivery.Controller;

import com.viola.coffeDelivery.CustomError;
import com.viola.coffeDelivery.model.Order;
import com.viola.coffeDelivery.service.OrderServiceImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import static org.springframework.http.RequestEntity.method;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController()
@RequestMapping("/order")
public class OrderController {
    
     @Autowired
     OrderServiceImp orderService;
    
    //-------------retrieve all orders-------------------//
     @RequestMapping( method = RequestMethod.GET)
      public ResponseEntity<List<Order>> listAllOrders(){
        List<Order> orderList = orderService.listAllOrders();
        if(orderList.isEmpty()){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Order>>(orderList,HttpStatus.OK);
     
    }
    
   //-------------retrieve  an order by ID-------------------//
    
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getOrder(@PathVariable ("id")int orderID)
    {
        
       Order order = orderService.findOrderById(orderID);
       if(order == null)
       {
       return new ResponseEntity(new CustomError("Order with id "+ orderID +"not found"),HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<Order>(order,HttpStatus.OK);
    
    }
    
    //-----------------create an order by ID-------------------//
    @RequestMapping(value = "create/{id}",method=RequestMethod.POST)
    public ResponseEntity<?> createOrder(@RequestBody Order order)
    {
    
    if(orderService.isOrderExist(order))
    {
    return new ResponseEntity(new CustomError("Unable to create order with id .Order already exists"+ order.getOrderID()),HttpStatus.CONFLICT );
    }
        orderService.saveOrder(order);
        return new ResponseEntity<Order>(order,HttpStatus.CREATED);
    }
    
    //-------------update an order by ID-------------------//
    
    @RequestMapping(value = "update/{id}",method =RequestMethod.POST)
    public ResponseEntity<?> updateOrder(@PathVariable("id")int orderID,@RequestBody Order order)
    {
         Order currentOrder = orderService.findOrderById(orderID);
         if(order == null )
         {
         return new ResponseEntity(new CustomError("Unable to update order with id " + + order.getOrderID()+".Order not found"),HttpStatus.NOT_FOUND);
         }
         
         currentOrder.setOrderID(order.getOrderID());
         currentOrder.setDataTime(order.getDataTime());
         currentOrder.setUserID(order.getUserID());
        
         orderService.updateOrder(currentOrder);
         return new ResponseEntity<Order>(currentOrder,HttpStatus.OK);
    }
    //-------------delete an order by ID-------------------//
    @RequestMapping(value = "delete/{id}" ,method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAnOrder(@PathVariable("id")int orderID)
    {
    Order order = orderService.findOrderById(orderID);
    
       if(order == null )
         {
         return new ResponseEntity(new CustomError("Unable to delete order with id .Order not found"+ order.getOrderID()),HttpStatus.NOT_FOUND);
         }
       orderService.deleteOrderById(orderID);
       return new ResponseEntity<Order>(order,HttpStatus.OK);
         
      }
    //-------------delete all Orders -------------------//
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public ResponseEntity<?>deleteAllOrders()
    {
        orderService.deleteAllOrders();
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
         
    }
    
    }
    
    
    
