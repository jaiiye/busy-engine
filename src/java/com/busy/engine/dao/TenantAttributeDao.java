


































package com.busy.engine.dao;

import com.busy.engine.entity.TenantAttribute;

public interface TenantAttributeDao extends IGenericDao<TenantAttribute, Integer>
{

        void getRelatedTenant(TenantAttribute tenant_attribute);
        void getRelatedTenantWithInfo(TenantAttribute tenant_attribute);        
         

      
}
    
