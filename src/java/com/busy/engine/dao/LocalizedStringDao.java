


































package com.busy.engine.dao;

import com.busy.engine.entity.LocalizedString;

public interface LocalizedStringDao extends IGenericDao<LocalizedString, Integer>
{

        void getRelatedTextString(LocalizedString localized_string);
        void getRelatedTextStringWithInfo(LocalizedString localized_string);        
         

      
}
    
