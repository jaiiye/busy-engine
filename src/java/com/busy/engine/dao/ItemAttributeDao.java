


































package com.busy.engine.dao;

import com.busy.engine.entity.ItemAttribute;

public interface ItemAttributeDao extends IGenericDao<ItemAttribute, Integer>
{

        void getRelatedItemAttributeType(ItemAttribute item_attribute);
        void getRelatedItemAttributeTypeWithInfo(ItemAttribute item_attribute);        
        
        void getRelatedItem(ItemAttribute item_attribute);
        void getRelatedItemWithInfo(ItemAttribute item_attribute);        
         

      
}
    
