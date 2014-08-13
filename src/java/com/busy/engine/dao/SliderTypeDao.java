



















package com.busy.engine.dao;

import com.busy.engine.entity.SliderType;

public interface SliderTypeDao extends IGenericDao<SliderType, Integer>
{
                    
      void getRelatedSliderList(SliderType slider_type);     
        
}
    

