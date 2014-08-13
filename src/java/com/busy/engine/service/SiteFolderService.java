














package com.busy.engine.service;

import com.busy.engine.entity.SiteFolder;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface SiteFolderService
{
      public Result<SiteFolder> find(String userName, Integer id);
      public Result<List<SiteFolder>> findAll(String userName); 
      public Result<SiteFolder> store(String userName, Integer siteFolderId, String folderName, String description, Integer rank, Integer siteId);
      public Result<SiteFolder> remove(String userName, Integer id);
}    








