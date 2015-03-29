


































package com.busy.engine.dao;

import com.busy.engine.entity.ItemLocation;

public interface ItemLocationDao extends IGenericDao<ItemLocation, Integer>
{

        void getRelatedItem(ItemLocation item_location);
        void getRelatedItemWithInfo(ItemLocation item_location);        
        
        void getRelatedAddress(ItemLocation item_location);
        void getRelatedAddressWithInfo(ItemLocation item_location);        
        
        void getRelatedContact(ItemLocation item_location);
        void getRelatedContactWithInfo(ItemLocation item_location);        
         

      
}
    
