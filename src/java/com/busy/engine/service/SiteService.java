

































package com.busy.engine.service;

import com.busy.engine.entity.Site;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface SiteService
{
      public Result<Site> find(String userName, Integer id);
      public Result<List<Site>> findAll(String userName); 
      public Result<Site> store(String userName, Integer siteId, String siteName, String domain, Integer mode, String url, String logoTitle, String logoImageUrl, Integer useAsStore, Integer siteStatus, String locale, Integer templateId, Integer siteEmailId, Integer dashboardId, Integer tenantId);
      public Result<Site> remove(String userName, Integer id);
}    




