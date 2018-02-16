package com.viola.coffeDelivery.Controller;

import com.viola.coffeDelivery.CustomError;
import com.viola.coffeDelivery.model.Shop;
import com.viola.coffeDelivery.service.ShopService;
import com.viola.coffeDelivery.service.ShopServiceImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/shops")
public class ShopController {
   
    
     @Autowired
     ShopServiceImp shopService;
     
     //-------------retrieve all shops -------------------//
     @RequestMapping( method = RequestMethod.GET)
     public ResponseEntity<List<Shop>> listAllShops()
     {
         
        List<Shop> shopList = shopService.listAllShops();
        if(shopList.isEmpty())
        {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Shop>>(shopList,HttpStatus.OK);
    
     }
    
     //-------------retrieve a  single shop  -------------------//
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getShop(@PathVariable("id") int shopID) 
    {
        
        Shop shop = shopService.findById(shopID);
        if (shop == null) 
        {
            return new ResponseEntity(new CustomError("User with id " + shopID
                                     + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Shop>(shop, HttpStatus.OK);

    }
     //-------------create  a  single shop  -------------------//
    @RequestMapping(value = "create/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> createShop(@RequestBody Shop shop)
    {
    if(shopService.isShopExist(shop))
    {
    return new ResponseEntity(new CustomError("Unable to create shop.Shop aleradyy exists"
                              +shop.getId()),HttpStatus.CONFLICT);
    }
    shopService.saveShop(shop);
    return new ResponseEntity<Shop>(shop,HttpStatus.CREATED);
    }
    

    
       //-------------delete a  single shop -------------------//
    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteShopByID(@PathVariable("id")int  shopId)
    {
    
    Shop shop = shopService.findById(shopId);
    if (shop == null)
    {
 
    return new ResponseEntity(new CustomError("Unable to delete shop with id " + shopId + "not found"),HttpStatus.NOT_FOUND);
    
    }
    shopService.deleteShopById(shopId);
    return new ResponseEntity<Shop>(shop,HttpStatus.NO_CONTENT);
    }
    
      //-----------------delete all  shop---------------------//
      @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
      public ResponseEntity<?>deleteAllShops()
      {
        shopService.deleteAllShops();
        return new ResponseEntity<Shop>(HttpStatus.NO_CONTENT);
            
      }
    
    
}
