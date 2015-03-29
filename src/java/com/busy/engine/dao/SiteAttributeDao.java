


































package com.busy.engine.dao;

import com.busy.engine.entity.SiteAttribute;

public interface SiteAttributeDao extends IGenericDao<SiteAttribute, Integer>
{

        void getRelatedSite(SiteAttribute site_attribute);
        void getRelatedSiteWithInfo(SiteAttribute site_attribute);        
         

      
}
    
