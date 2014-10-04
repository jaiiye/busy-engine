


































package com.busy.engine.dao;

import com.busy.engine.entity.SiteFolder;

public interface SiteFolderDao extends IGenericDao<SiteFolder, Integer>
{
                    
      void getRelatedFileFolderList(SiteFolder site_folder);     
        
}
    

