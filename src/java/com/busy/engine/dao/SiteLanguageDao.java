


































package com.busy.engine.dao;

import com.busy.engine.entity.SiteLanguage;

public interface SiteLanguageDao extends IGenericDao<SiteLanguage, Integer>
{

        void getRelatedSite(SiteLanguage site_language);
        void getRelatedSiteWithInfo(SiteLanguage site_language);        
         

      
}
    
