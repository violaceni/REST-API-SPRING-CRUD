package com.viola.coffeDelivery.service;

import com.viola.coffeDelivery.model.Category;
import java.util.List;


public interface CategoryService {
    
    List<Category> listAllCategory();
    
    Category findCategoryById(int categoryID);
    
    void saveCategory(Category category);
    
    void deleteCategoryById (int categoryID);
    
    void updateOrder(Category category);
    
    void deleteAllCategories();
    
    boolean isCategoryExist(Category category);
    
}
