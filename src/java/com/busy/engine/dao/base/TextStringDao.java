



















package com.busy.engine.dao.base;

import com.busy.engine.domain.TextString;

public interface TextStringDao extends IGenericDao<TextString, Integer>
{
                    
      TextString getRelatedLocalizedStringList(TextString text_string);     
                      
        
}
    

