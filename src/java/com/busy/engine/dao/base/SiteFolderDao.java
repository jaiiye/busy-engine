



















package com.busy.engine.dao.base;

import com.busy.engine.domain.SiteFolder;

public interface SiteFolderDao extends IGenericDao<SiteFolder, Integer>
{
                    
      SiteFolder getRelatedFileFolderList(SiteFolder site_folder);     
        
}
    

