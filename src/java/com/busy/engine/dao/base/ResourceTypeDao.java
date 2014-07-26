



















package com.busy.engine.dao.base;

import com.busy.engine.domain.ResourceType;

public interface ResourceTypeDao extends IGenericDao<ResourceType, Integer>
{
                    
      ResourceType getRelatedResourceUrlList(ResourceType resource_type);     
        
}
    

