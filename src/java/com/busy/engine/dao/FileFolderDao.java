


































package com.busy.engine.dao;

import com.busy.engine.entity.FileFolder;

public interface FileFolderDao extends IGenericDao<FileFolder, Integer>
{

        void getRelatedSiteFile(FileFolder file_folder);
        void getRelatedSiteFileWithInfo(FileFolder file_folder);        
        
        void getRelatedSiteFolder(FileFolder file_folder);
        void getRelatedSiteFolderWithInfo(FileFolder file_folder);        
         

      
}
    
