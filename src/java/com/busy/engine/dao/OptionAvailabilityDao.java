


































package com.busy.engine.dao;

import com.busy.engine.entity.OptionAvailability;

public interface OptionAvailabilityDao extends IGenericDao<OptionAvailability, Integer>
{

        void getRelatedItem(OptionAvailability option_availability);
        void getRelatedItemWithInfo(OptionAvailability option_availability);        
        
        void getRelatedItemOption(OptionAvailability option_availability);
        void getRelatedItemOptionWithInfo(OptionAvailability option_availability);        
        
        void getRelatedItemAvailability(OptionAvailability option_availability);
        void getRelatedItemAvailabilityWithInfo(OptionAvailability option_availability);        
         

      
}
    
