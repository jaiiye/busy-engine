



















package com.busy.engine.dao.base;

import com.busy.engine.domain.FormFieldType;

public interface FormFieldTypeDao extends IGenericDao<FormFieldType, Integer>
{
                    
      FormFieldType getRelatedFormFieldList(FormFieldType form_field_type);     
        
}
    

