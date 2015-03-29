


































package com.busy.engine.dao;

import com.busy.engine.entity.SiteFolder;

public interface SiteFolderDao extends IGenericDao<SiteFolder, Integer>
{

        void getRelatedSite(SiteFolder site_folder);
        void getRelatedSiteWithInfo(SiteFolder site_folder);        
         

    void getRelatedFileFolderList(SiteFolder site_folder);
      
}
    
