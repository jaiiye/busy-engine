



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Form;

public interface FormDao extends IGenericDao<Form, Integer>
{
                    
      Form getRelatedFormFieldList(Form form);     
                  
      Form getRelatedPageList(Form form);     
                  
      Form getRelatedSliderList(Form form);     
        
}
    

