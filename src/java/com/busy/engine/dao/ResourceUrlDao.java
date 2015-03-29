


































package com.busy.engine.dao;

import com.busy.engine.entity.ResourceUrl;

public interface ResourceUrlDao extends IGenericDao<ResourceUrl, Integer>
{

        void getRelatedTemplate(ResourceUrl resource_url);
        void getRelatedTemplateWithInfo(ResourceUrl resource_url);        
        
        void getRelatedResourceType(ResourceUrl resource_url);
        void getRelatedResourceTypeWithInfo(ResourceUrl resource_url);        
         

      
}
    
