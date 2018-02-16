package com.viola.coffeDelivery.service;

import com.viola.coffeDelivery.model.Category;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImp implements CategoryService {
    
     private static final AtomicLong counter = new AtomicLong();
    
    private static List<Category> categories = new ArrayList<>();

    static {
       
           categories = populateCategory();
        } 

    @Override
    public List<Category> listAllCategory() {
       return categories;
    }

    @Override
    public Category findCategoryById(int categoryID) {
         for(Category category:categories){
        if(category.getCategoryID()== categoryID)
         return category;
        }
    return null;
    }

    @Override
    public void saveCategory(Category category ) 
    {
    category.setCategoryID((int) counter.incrementAndGet());
    categories.add(category);
    }

    @Override
    public void deleteCategoryById(int categoryID) 
    {
     for(Iterator<Category> iterator =categories.iterator();iterator.hasNext(); ){
     Category category = iterator.next();
     if(category.getCategoryID()==categoryID)
         iterator.remove();
    }
    }

    @Override
    public void updateOrder(Category category) 
    {
     int index = categories.indexOf(category);
     categories.set(index,category);
    }

    @Override
    public void deleteAllCategories() {
         categories.clear();
    }

    @Override
    public boolean isCategoryExist(Category category) {
        return findCategoryById(category.getCategoryID())!= null;
    }
    
    private static List<Category> populateCategory() {
      
        List<Category> categories = new ArrayList<Category>();
        categories.add(new Category((int) counter.incrementAndGet(), "Coffee", "Small coffee "));
        categories.add(new Category((int) counter.incrementAndGet(), "American coffee","Medium coffee" ));      
        categories.add(new Category((int) counter.incrementAndGet(), "Frappucino", "Big")); 
        categories.add(new Category((int) counter.incrementAndGet(), "Fruit-Salad", "Medium"));
        categories.add(new Category((int) counter.incrementAndGet(), "Cake","Normal"));
        return categories;
    }
    
    
}
