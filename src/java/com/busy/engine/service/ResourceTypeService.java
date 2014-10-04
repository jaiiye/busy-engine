

































package com.busy.engine.service;

import com.busy.engine.entity.ResourceType;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ResourceTypeService
{
      public Result<ResourceType> find(String userName, Integer id);
      public Result<List<ResourceType>> findAll(String userName); 
      public Result<ResourceType> store(String userName, Integer resourceTypeId, String typeName, String typeValue);
      public Result<ResourceType> remove(String userName, Integer id);
}    




