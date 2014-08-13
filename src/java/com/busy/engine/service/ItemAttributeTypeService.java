














package com.busy.engine.service;

import com.busy.engine.entity.ItemAttributeType;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ItemAttributeTypeService
{
      public Result<ItemAttributeType> find(String userName, Integer id);
      public Result<List<ItemAttributeType>> findAll(String userName); 
      public Result<ItemAttributeType> store(String userName, Integer itemAttributeTypeId, String attributeName, String description);
      public Result<ItemAttributeType> remove(String userName, Integer id);
}    








