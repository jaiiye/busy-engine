


































package com.busy.engine.dao;

import com.busy.engine.entity.Category;

public interface CategoryDao extends IGenericDao<Category, Integer>
{

        void getRelatedDiscount(Category category);
        void getRelatedDiscountWithInfo(Category category);        
        
        void getRelatedParentCategory(Category category);
        void getRelatedParentCategoryWithInfo(Category category);        
         

    void getRelatedItemCategoryList(Category category);
      
}
    
