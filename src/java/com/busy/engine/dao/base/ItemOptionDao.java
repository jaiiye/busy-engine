



















package com.busy.engine.dao.base;

import com.busy.engine.domain.ItemOption;

public interface ItemOptionDao extends IGenericDao<ItemOption, Integer>
{
                    
      ItemOption getRelatedOptionAvailabilityList(ItemOption item_option);     
        
}
    

