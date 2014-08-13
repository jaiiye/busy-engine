



















package com.busy.engine.dao;

import com.busy.engine.entity.FormFieldType;

public interface FormFieldTypeDao extends IGenericDao<FormFieldType, Integer>
{
                    
      void getRelatedFormFieldList(FormFieldType form_field_type);     
        
}
    

