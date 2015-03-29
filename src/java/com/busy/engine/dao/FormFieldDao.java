


































package com.busy.engine.dao;

import com.busy.engine.entity.FormField;

public interface FormFieldDao extends IGenericDao<FormField, Integer>
{

        void getRelatedFormFieldType(FormField form_field);
        void getRelatedFormFieldTypeWithInfo(FormField form_field);        
        
        void getRelatedForm(FormField form_field);
        void getRelatedFormWithInfo(FormField form_field);        
         

      
}
    
