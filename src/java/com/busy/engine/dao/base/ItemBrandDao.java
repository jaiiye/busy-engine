



















package com.busy.engine.dao.base;

import com.busy.engine.domain.ItemBrand;

public interface ItemBrandDao extends IGenericDao<ItemBrand, Integer>
{
                    
      ItemBrand getRelatedItemList(ItemBrand item_brand);     
                  
      ItemBrand getRelatedUserList(ItemBrand item_brand);     
        
}
    

