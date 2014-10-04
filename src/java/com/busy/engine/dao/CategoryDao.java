


































package com.busy.engine.dao;

import com.busy.engine.entity.Category;

public interface CategoryDao extends IGenericDao<Category, Integer>
{
                    
      void getRelatedItemCategoryList(Category category);     
        
}
    

