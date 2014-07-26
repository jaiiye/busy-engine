



















package com.busy.engine.dao.base;

import com.busy.engine.domain.TemplateType;

public interface TemplateTypeDao extends IGenericDao<TemplateType, Integer>
{
                    
      TemplateType getRelatedTemplateList(TemplateType template_type);     
        
}
    

