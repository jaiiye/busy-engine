


































package com.busy.engine.dao;

import com.busy.engine.entity.SiteImage;

public interface SiteImageDao extends IGenericDao<SiteImage, Integer>
{

        void getRelatedImageType(SiteImage site_image);
        void getRelatedImageTypeWithInfo(SiteImage site_image);        
        
        void getRelatedSite(SiteImage site_image);
        void getRelatedSiteWithInfo(SiteImage site_image);        
         

      
}
    
