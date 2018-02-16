package com.viola.coffeDelivery.service;

import com.viola.coffeDelivery.model.Shop;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service("shopService")
public class ShopServiceImp implements ShopService {
    
    
   private static final AtomicLong  counter = new AtomicLong();
   private static List<Shop> shops;
   static { shops = populateShop();}
   
    public List<Shop> listAllShops(){
        return shops;
    
    }
    @Override
    public Shop findById(int shopId){
        for(Shop shop : shops){
        if(shop.getId()==shopId){
        return shop;
        }
        }
    return null;
    }
    @Override
    public Shop findByName(String name){
    for(Shop shop : shops){
    if(shop.getName()== name){
     return shop;
         }
    }
    return null;
    }
   @Override
   public void saveShop(Shop shop){
       shop.setId((int) counter.incrementAndGet());
       shops.add(shop);
   
   }
   @Override
   public void  updateShop(Shop shop ){
       int index = shops.indexOf(shop);
       shops.set(index, shop);
   }
   
   
    @Override
    public void deleteShopById(int shopID) {
     for (Iterator<Shop> iterator = shops.iterator(); iterator.hasNext();) {
            Shop shop = iterator.next();
            if (shop.getId() == shopID) {
                iterator.remove();
            }
   }    }
 
   @Override
   public void deleteAllShops(){
   shops.clear();
   }
    
     private static List<Shop> populateShop() {
      
        List<Shop> shops = new ArrayList<Shop>();
        shops.add(new Shop((int) counter.incrementAndGet(), "Moncheri", 30.40 , 20.40,1));
        shops.add(new Shop((int) counter.incrementAndGet(), "Moncheri", 34.40 , 27.40,1));      
        shops.add(new Shop((int) counter.incrementAndGet(), "Mulliri i vjeter", 40.40 , 2140,1)); 
        shops.add(new Shop((int) counter.incrementAndGet(), "Sophie", 38.40 , 29.40,1));
        shops.add(new Shop((int) counter.incrementAndGet(), "Sophie", 30.40 , 20.40,1));
        return shops;
    }
     
    @Override
    public boolean isShopExist(Shop shop) {
     return findByName(shop.getName()) != null;    }
    
}
