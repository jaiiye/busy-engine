



















package com.busy.engine.dao;

import com.busy.engine.entity.ItemAvailability;

public interface ItemAvailabilityDao extends IGenericDao<ItemAvailability, Integer>
{
                    
      void getRelatedOptionAvailabilityList(ItemAvailability item_availability);     
        
}
    

