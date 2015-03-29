


































package com.busy.engine.dao;

import com.busy.engine.entity.SitePage;

public interface SitePageDao extends IGenericDao<SitePage, Integer>
{

        void getRelatedSite(SitePage site_page);
        void getRelatedSiteWithInfo(SitePage site_page);        
        
        void getRelatedPage(SitePage site_page);
        void getRelatedPageWithInfo(SitePage site_page);        
         

      
}
    
