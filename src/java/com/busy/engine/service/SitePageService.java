














package com.busy.engine.service;

import com.busy.engine.entity.SitePage;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface SitePageService
{
      public Result<SitePage> find(String userName, Integer id);
      public Result<List<SitePage>> findAll(String userName); 
      public Result<SitePage> store(String userName, Integer sitePageId, Integer siteId, Integer pageId);
      public Result<SitePage> remove(String userName, Integer id);
}    








