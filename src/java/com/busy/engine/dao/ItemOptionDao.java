



















package com.busy.engine.dao;

import com.busy.engine.entity.ItemOption;

public interface ItemOptionDao extends IGenericDao<ItemOption, Integer>
{
                    
      void getRelatedOptionAvailabilityList(ItemOption item_option);     
        
}
    

