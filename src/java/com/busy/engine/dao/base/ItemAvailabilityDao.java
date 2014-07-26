



















package com.busy.engine.dao.base;

import com.busy.engine.domain.ItemAvailability;

public interface ItemAvailabilityDao extends IGenericDao<ItemAvailability, Integer>
{
                    
      ItemAvailability getRelatedOptionAvailabilityList(ItemAvailability item_availability);     
        
}
    

