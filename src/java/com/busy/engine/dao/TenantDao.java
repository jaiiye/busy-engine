


































package com.busy.engine.dao;

import com.busy.engine.entity.Tenant;

public interface TenantDao extends IGenericDao<Tenant, Integer>
{
                    
      void getRelatedSiteList(Tenant tenant);     
                  
      void getRelatedTenantAttributeList(Tenant tenant);     
        
}
    

