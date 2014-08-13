














package com.busy.engine.service;

import com.busy.engine.entity.SiteItem;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface SiteItemService
{
      public Result<SiteItem> find(String userName, Integer id);
      public Result<List<SiteItem>> findAll(String userName); 
      public Result<SiteItem> store(String userName, Integer siteItemId, Integer siteId, Integer itemId);
      public Result<SiteItem> remove(String userName, Integer id);
}    








