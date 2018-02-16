package com.viola.coffeDelivery.Controller;

import com.viola.coffeDelivery.CustomError;
import com.viola.coffeDelivery.model.Category;
import com.viola.coffeDelivery.service.CategoryServiceImp;
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
@RequestMapping("category")
public class CategoryController {
    
    @Autowired
    CategoryServiceImp categoryService;
    
    //-------------retrieve all categories-------------------//
     @RequestMapping( method = RequestMethod.GET)
      public ResponseEntity<List< Category>> listAllCategory(){
        List< Category> categoryList = categoryService.listAllCategory();
        if(categoryList.isEmpty()){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List< Category>>(categoryList,HttpStatus.OK);
     
    }
      
         //-------------retrieve  an category by ID-------------------//
    
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getCategory(@PathVariable ("id")int categoryID)
    {
        
       Category category = categoryService.findCategoryById(categoryID);
       if(category == null)
       {
       return new ResponseEntity(new CustomError("Order with id "+ categoryID +"not found"),HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<Category>(category,HttpStatus.OK);
    
    }
    
    //-----------------create an Category by ID-------------------//
    @RequestMapping(value = "create/{id}",method=RequestMethod.POST)
    public ResponseEntity<?> createCategory(@RequestBody Category category)
    {
    
    if(categoryService.isCategoryExist(category))
    {
    return new ResponseEntity(new CustomError("Unable to create category with id .Category already exists"+ category.getCategoryID()),HttpStatus.CONFLICT );
    }
        categoryService.saveCategory(category);
        return new ResponseEntity<Category>(category,HttpStatus.CREATED);
    }
    
    
        //-------------update an category by ID-------------------//
    
    @RequestMapping(value = "update/{id}",method =RequestMethod.POST)
    public ResponseEntity<?> updateCategory(@PathVariable("id")int categoryID,@RequestBody Category category)
    {
         Category currentCategory = categoryService.findCategoryById(categoryID);
         if(category == null )
         {
         return new ResponseEntity(new CustomError("Unable to update order with id " + + category.getCategoryID()+".Order not found"),HttpStatus.NOT_FOUND);
         }
         
         currentCategory.setCategoryID(category.getCategoryID());
         currentCategory.setName(category.getName());
         currentCategory.setDescription(category.getDescription());
        
         categoryService.updateOrder(currentCategory);
         return new ResponseEntity<Category>(currentCategory,HttpStatus.OK);
    }
    
      //-------------delete an category by ID-------------------//
    @RequestMapping(value = "delete/{id}" ,method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAnCategory(@PathVariable("id")int categoryID)
    {
    Category category = categoryService.findCategoryById(categoryID);
    
       if(category == null )
         {
         return new ResponseEntity(new CustomError("Unable to category order with id .Category not found"+ category.getCategoryID()),HttpStatus.NOT_FOUND);
         }
       categoryService.deleteCategoryById(categoryID);
       return new ResponseEntity<Category>(category,HttpStatus.OK);
         
      }
    
      //-------------delete all Orders -------------------//
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public ResponseEntity<?>deleteCategories()
    {
        categoryService.deleteAllCategories();
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
         
    }
    
}
    
    
    
