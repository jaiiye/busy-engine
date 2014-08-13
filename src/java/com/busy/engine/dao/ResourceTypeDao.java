



















package com.busy.engine.dao;

import com.busy.engine.entity.ResourceType;

public interface ResourceTypeDao extends IGenericDao<ResourceType, Integer>
{
                    
      void getRelatedResourceUrlList(ResourceType resource_type);     
        
}
    

