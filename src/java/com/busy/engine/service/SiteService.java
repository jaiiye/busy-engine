














package com.busy.engine.service;

import com.busy.engine.entity.Site;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface SiteService
{
      public Result<Site> find(String userName, Integer id);
      public Result<List<Site>> findAll(String userName); 
      public Result<Site> store(String userName, Integer siteId, String siteName, String domain, Integer mode, String url, String logoTitle, String logoImage, Integer useAsStore, String emailHost, Integer emailPort, String emailUsername, String emailPassword, Integer siteStatus, String locale, Integer templateId);
      public Result<Site> remove(String userName, Integer id);
}    








