



















package com.busy.engine.dao.base;

import com.busy.engine.domain.SliderType;

public interface SliderTypeDao extends IGenericDao<SliderType, Integer>
{
                    
      SliderType getRelatedSliderList(SliderType slider_type);     
        
}
    

