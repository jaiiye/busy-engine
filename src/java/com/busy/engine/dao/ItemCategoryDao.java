


































package com.busy.engine.dao;

import com.busy.engine.entity.ItemCategory;

public interface ItemCategoryDao extends IGenericDao<ItemCategory, Integer>
{

        void getRelatedCategory(ItemCategory item_category);
        void getRelatedCategoryWithInfo(ItemCategory item_category);        
        
        void getRelatedItem(ItemCategory item_category);
        void getRelatedItemWithInfo(ItemCategory item_category);        
         

      
}
    
