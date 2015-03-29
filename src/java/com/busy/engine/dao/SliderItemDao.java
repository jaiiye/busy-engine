


































package com.busy.engine.dao;

import com.busy.engine.entity.SliderItem;

public interface SliderItemDao extends IGenericDao<SliderItem, Integer>
{

        void getRelatedSlider(SliderItem slider_item);
        void getRelatedSliderWithInfo(SliderItem slider_item);        
         

      
}
    
