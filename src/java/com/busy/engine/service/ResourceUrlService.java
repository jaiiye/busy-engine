














package com.busy.engine.service;

import com.busy.engine.entity.ResourceUrl;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ResourceUrlService
{
      public Result<ResourceUrl> find(String userName, Integer id);
      public Result<List<ResourceUrl>> findAll(String userName); 
      public Result<ResourceUrl> store(String userName, Integer resourceUrlId, String url, Integer templateId, Integer resourceTypeId);
      public Result<ResourceUrl> remove(String userName, Integer id);
}    








