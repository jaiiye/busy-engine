


































package com.busy.engine.dao;

import com.busy.engine.entity.ItemImage;

public interface ItemImageDao extends IGenericDao<ItemImage, Integer>
{

        void getRelatedItem(ItemImage item_image);
        void getRelatedItemWithInfo(ItemImage item_image);        
         

      
}
    
