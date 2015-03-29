


































package com.busy.engine.dao;

import com.busy.engine.entity.TextString;

public interface TextStringDao extends IGenericDao<TextString, Integer>
{
 

    void getRelatedLocalizedStringList(TextString text_string);
      
}
    
