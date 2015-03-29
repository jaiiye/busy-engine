


































package com.busy.engine.dao;

import com.busy.engine.entity.Form;

public interface FormDao extends IGenericDao<Form, Integer>
{
 

    void getRelatedFormFieldList(Form form);
    void getRelatedPageList(Form form);
    void getRelatedSliderList(Form form);
      
}
    
