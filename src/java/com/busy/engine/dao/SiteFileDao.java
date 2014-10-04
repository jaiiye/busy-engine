


































package com.busy.engine.dao;

import com.busy.engine.entity.SiteFile;

public interface SiteFileDao extends IGenericDao<SiteFile, Integer>
{
                    
      void getRelatedFileFolderList(SiteFile site_file);     
        
}
    

