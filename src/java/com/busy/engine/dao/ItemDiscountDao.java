


































package com.busy.engine.dao;

import com.busy.engine.entity.ItemDiscount;

public interface ItemDiscountDao extends IGenericDao<ItemDiscount, Integer>
{

        void getRelatedItem(ItemDiscount item_discount);
        void getRelatedItemWithInfo(ItemDiscount item_discount);        
        
        void getRelatedDiscount(ItemDiscount item_discount);
        void getRelatedDiscountWithInfo(ItemDiscount item_discount);        
         

      
}
    
