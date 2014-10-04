


































package com.busy.engine.dao;

import com.busy.engine.entity.ItemAttributeType;

public interface ItemAttributeTypeDao extends IGenericDao<ItemAttributeType, Integer>
{
                    
      void getRelatedItemAttributeList(ItemAttributeType item_attribute_type);     
        
}
    

