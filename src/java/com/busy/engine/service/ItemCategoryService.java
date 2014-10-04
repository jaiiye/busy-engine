

































package com.busy.engine.service;

import com.busy.engine.entity.ItemCategory;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ItemCategoryService
{
      public Result<ItemCategory> find(String userName, Integer id);
      public Result<List<ItemCategory>> findAll(String userName); 
      public Result<ItemCategory> store(String userName, Integer itemCategoryId, Integer categoryId, Integer itemId);
      public Result<ItemCategory> remove(String userName, Integer id);
}    




