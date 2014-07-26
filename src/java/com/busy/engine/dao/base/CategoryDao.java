



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Category;

public interface CategoryDao extends IGenericDao<Category, Integer>
{
                    
      Category getRelatedItemCategoryList(Category category);     
        
}
    

