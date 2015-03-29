


































package com.busy.engine.dao;

import com.busy.engine.entity.Tenant;

public interface TenantDao extends IGenericDao<Tenant, Integer>
{

        void getRelatedDashboard(Tenant tenant);
        void getRelatedDashboardWithInfo(Tenant tenant);        
         

    void getRelatedSiteList(Tenant tenant);
    void getRelatedTenantAttributeList(Tenant tenant);
      
}
    
