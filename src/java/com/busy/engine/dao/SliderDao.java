


































package com.busy.engine.dao;

import com.busy.engine.entity.Slider;

public interface SliderDao extends IGenericDao<Slider, Integer>
{

        void getRelatedSliderType(Slider slider);
        void getRelatedSliderTypeWithInfo(Slider slider);        
        
        void getRelatedForm(Slider slider);
        void getRelatedFormWithInfo(Slider slider);        
         

    void getRelatedPageList(Slider slider);
    void getRelatedSliderItemList(Slider slider);
      
}
    
