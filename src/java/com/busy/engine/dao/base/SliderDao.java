



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Slider;

public interface SliderDao extends IGenericDao<Slider, Integer>
{
                    
      Slider getRelatedPageList(Slider slider);     
                  
      Slider getRelatedSliderItemList(Slider slider);     
        
}
    

