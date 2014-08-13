



















package com.busy.engine.dao;

import com.busy.engine.entity.Slider;

public interface SliderDao extends IGenericDao<Slider, Integer>
{
                    
      void getRelatedPageList(Slider slider);     
                  
      void getRelatedSliderItemList(Slider slider);     
        
}
    

