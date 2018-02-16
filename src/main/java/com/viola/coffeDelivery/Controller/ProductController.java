package com.viola.coffeDelivery.Controller;

import com.viola.coffeDelivery.CustomError;
import com.viola.coffeDelivery.model.Product;
import com.viola.coffeDelivery.service.ProductServiceImp;
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
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    ProductServiceImp productService;
    
     //-------------retrieve all products------------------//
     @RequestMapping( method = RequestMethod.GET)
      public ResponseEntity<List<Product>> listAllProduct()
      {
        List<Product> productList = productService.listAllProduct();
        if(productList.isEmpty()){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
     
    }
      
       //-----------------retrieve all a single product---------------------//
      
      @RequestMapping(value ="{id}",method = RequestMethod.GET)
      public ResponseEntity<?> getProduct(@PathVariable("id")int productID)
      {
          Product product = productService.findById(productID);
          if(product == null)
          {
              return new ResponseEntity(new CustomError("Unable to find product with id"+ productID +"product does not exist"),HttpStatus.NOT_FOUND);
          }
         
      return new ResponseEntity<Product>(product,HttpStatus.OK);
      }
      
      //-----------------create  a  product---------------------//
      @RequestMapping(value = "create/{id}", method = RequestMethod.POST)
      public ResponseEntity<?> createProduct(@RequestBody Product product)
      {
         if(productService.isProductExist(product))
         {
             return new ResponseEntity(new CustomError("Unable to create product with id "+product.getProductID()+".Product already exists"),HttpStatus.CONFLICT);
         }
         productService.saveProduct(product);
        return new ResponseEntity<Product>(product,HttpStatus.CREATED);
      }
      
      
       //-----------------update a  product---------------------//
      
      @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
      public ResponseEntity<?> updateProduct(@PathVariable("id") int productID ,@RequestBody Product product)
      
      {
    Product  currentProduct = productService.findById(productID);
          if(currentProduct == null)
      {
          return new ResponseEntity(new CustomError("Unable to update product with id" + productID + "product does not exist"),HttpStatus.NOT_FOUND);
      }
   
      currentProduct.setProductID(product.getProductID());
      currentProduct.setCategoryID(product.getCategoryID());
      currentProduct.setName(product.getName());
      currentProduct.setPrice(product.getPrice());
      currentProduct.setQuantity(product.getQuantity());
      productService.updateProduct(currentProduct);
       
      return new ResponseEntity<Product>(currentProduct,HttpStatus.OK);
      }
      
      
       //-----------------delete all  products---------------------//
      @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
      public ResponseEntity<?>deleteAllProducts()
      {
        productService.deleteAllProduct();
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
            
      }
      
        //-----------------delete a single product---------------------//
      @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
      public ResponseEntity<?> deleteProductByID(@PathVariable("id")int productID)
      {
         Product product = productService.findById(productID);
         if(product == null )
         {
         return new ResponseEntity(new CustomError("Unable to delete product with id" +productID +"Product does not exists"),HttpStatus.NOT_FOUND);
         }
         productService.deleteProductById(productID);
        return new ResponseEntity<Product>(product,HttpStatus.OK) ;
      
      }
      
      
}
