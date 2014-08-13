














package com.busy.engine.service;

import com.busy.engine.entity.SiteImage;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface SiteImageService
{
      public Result<SiteImage> find(String userName, Integer id);
      public Result<List<SiteImage>> findAll(String userName); 
      public Result<SiteImage> store(String userName, Integer siteImageId, String fileName, String description, String linkUrl, Integer rank, Integer imageTypeId, Integer siteId);
      public Result<SiteImage> remove(String userName, Integer id);
}    








