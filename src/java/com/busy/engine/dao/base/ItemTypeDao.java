



















package com.busy.engine.dao.base;

import com.busy.engine.domain.ItemType;

public interface ItemTypeDao extends IGenericDao<ItemType, Integer>
{
                    
      ItemType getRelatedItemList(ItemType item_type);     
        
}
    

