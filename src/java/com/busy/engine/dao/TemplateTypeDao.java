


































package com.busy.engine.dao;

import com.busy.engine.entity.TemplateType;

public interface TemplateTypeDao extends IGenericDao<TemplateType, Integer>
{
 

    void getRelatedTemplateList(TemplateType template_type);
      
}
    
