



















package com.busy.engine.dao.base;

import com.busy.engine.domain.SiteFile;

public interface SiteFileDao extends IGenericDao<SiteFile, Integer>
{
                    
      SiteFile getRelatedFileFolderList(SiteFile site_file);     
        
}
    

