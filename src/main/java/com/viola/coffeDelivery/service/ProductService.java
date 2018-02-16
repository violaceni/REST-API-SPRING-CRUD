
package com.viola.coffeDelivery.service;


import com.viola.coffeDelivery.model.Product;
import java.util.List;


public interface ProductService {
    
        
     List<Product> listAllProduct();
    
    Product findById(int productID);
    
    Product findByName(String name);
    
    void saveProduct(Product product);
    
    void deleteProductById (int productID);
    
    void updateProduct(Product product);
    
    void deleteAllProduct();
    
    boolean isProductExist(Product product);
    
}
