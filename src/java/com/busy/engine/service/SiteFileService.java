

































package com.busy.engine.service;

import com.busy.engine.entity.SiteFile;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface SiteFileService
{
      public Result<SiteFile> find(String userName, Integer id);
      public Result<List<SiteFile>> findAll(String userName); 
      public Result<SiteFile> store(String userName, Integer siteFileId, String fileName, String description, String label);
      public Result<SiteFile> remove(String userName, Integer id);
}    




