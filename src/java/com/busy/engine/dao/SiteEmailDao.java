


































package com.busy.engine.dao;

import com.busy.engine.entity.SiteEmail;

public interface SiteEmailDao extends IGenericDao<SiteEmail, Integer>
{
 

    void getRelatedSiteList(SiteEmail site_email);
      
}
    
