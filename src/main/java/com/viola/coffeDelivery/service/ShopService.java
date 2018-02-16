
package com.viola.coffeDelivery.service;

import com.viola.coffeDelivery.model.Shop;
import java.util.List;


public interface ShopService {
    
     List<Shop> listAllShops();
    
    Shop findById(int shopID);
    
    Shop findByName(String name);
    
    void saveShop(Shop shop);
    
    void deleteShopById (int shopID);
    
    void updateShop(Shop shop);
    
    void deleteAllShops();
    
    boolean isShopExist(Shop shop);
    
}
