


































package com.busy.engine.dao;

import com.busy.engine.entity.ItemBrand;

public interface ItemBrandDao extends IGenericDao<ItemBrand, Integer>
{
                    
      void getRelatedItemList(ItemBrand item_brand);     
                  
      void getRelatedUserList(ItemBrand item_brand);     
        
}
    

