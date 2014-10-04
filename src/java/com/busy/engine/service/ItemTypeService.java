

































package com.busy.engine.service;

import com.busy.engine.entity.ItemType;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ItemTypeService
{
      public Result<ItemType> find(String userName, Integer id);
      public Result<List<ItemType>> findAll(String userName); 
      public Result<ItemType> store(String userName, Integer itemTypeId, String typeName, Integer rank);
      public Result<ItemType> remove(String userName, Integer id);
}    




