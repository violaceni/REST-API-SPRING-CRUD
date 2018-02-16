package com.viola.coffeDelivery.service;

import com.viola.coffeDelivery.model.Product;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImp implements ProductService {
    
   private static final AtomicLong  counter = new AtomicLong();
   private static List<Product> products;
   static { products = populateProduct();}
   public List<Product> listAllShops(){
        return products;
   }

       private static List<Product> populateProduct() {
      
        List<Product> products = new ArrayList<Product>();
        products.add(new Product((int) counter.incrementAndGet(), "Coffee", 30.40 , 20.40,1));
        products.add(new Product((int) counter.incrementAndGet(), "American coffee", 34.40 , 27.40,1));      
        products.add(new Product((int) counter.incrementAndGet(), "Frappucino", 40.40 , 2140,1)); 
        products.add(new Product((int) counter.incrementAndGet(), "Fruit-Salad", 38.40 , 29.40,1));
        products.add(new Product((int) counter.incrementAndGet(), "Cake", 30.40 , 20.40,1));
        return products;
    }

    
    public List<Product> listAllProduct() {
        return products;
    }
    
    public Product findById(int productID) {
   
     for(Product product :products )
              {
       if(product.getProductID()== productID)
           return product ;
              }
       return null;
       
    }

    @Override
    public Product findByName(String name) 
       {
            for(Product product : products){
            if(product.getName()== name){
            return product;
         }
    }
            return null;
    }
    

    @Override
    public void saveProduct(Product product) 
    {
       product.setProductID((int) counter.incrementAndGet());
       products.add(product);
    
    }

    @Override
    public void deleteProductById(int productID) 
    {
            for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) 
            {
            Product product = iterator.next();
            if (product.getProductID() == productID) {
                iterator.remove();
            }
            }
            
    }
    
    @Override
    public void updateProduct(Product product) 
    {
        
    int index = products.indexOf(product);
       products.set(index, product);
    }

    @Override
    public void deleteAllProduct() {
        
     products.clear();
   
    }

    @Override
    public boolean isProductExist(Product product) {
         return findByName(product.getName()) != null; 
    }
    
}
