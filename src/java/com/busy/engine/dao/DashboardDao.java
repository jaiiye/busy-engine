


































package com.busy.engine.dao;

import com.busy.engine.entity.Dashboard;

public interface DashboardDao extends IGenericDao<Dashboard, Integer>
{
 

    void getRelatedSiteList(Dashboard dashboard);
    void getRelatedTenantList(Dashboard dashboard);
      
}
    
