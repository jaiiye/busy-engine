


































package com.busy.engine.dao;

import com.busy.engine.entity.ItemType;

public interface ItemTypeDao extends IGenericDao<ItemType, Integer>
{
 

    void getRelatedItemList(ItemType item_type);
      
}
    
