














package com.busy.engine.service;

import com.busy.engine.entity.SiteAttribute;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface SiteAttributeService
{
      public Result<SiteAttribute> find(String userName, Integer id);
      public Result<List<SiteAttribute>> findAll(String userName); 
      public Result<SiteAttribute> store(String userName, Integer siteAttributeId, String attributeKey, String attributeValue, String attributeType, Integer siteId);
      public Result<SiteAttribute> remove(String userName, Integer id);
}    








