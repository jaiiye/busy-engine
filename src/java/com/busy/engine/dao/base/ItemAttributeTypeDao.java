



















package com.busy.engine.dao.base;

import com.busy.engine.domain.ItemAttributeType;

public interface ItemAttributeTypeDao extends IGenericDao<ItemAttributeType, Integer>
{
                    
      ItemAttributeType getRelatedItemAttributeList(ItemAttributeType item_attribute_type);     
        
}
    

