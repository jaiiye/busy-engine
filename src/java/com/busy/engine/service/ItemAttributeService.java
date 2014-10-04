

































package com.busy.engine.service;

import com.busy.engine.entity.ItemAttribute;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ItemAttributeService
{
      public Result<ItemAttribute> find(String userName, Integer id);
      public Result<List<ItemAttribute>> findAll(String userName); 
      public Result<ItemAttribute> store(String userName, Integer itemAttributeId, String key, String value, String locale, Integer itemAttributeTypeId, Integer itemId);
      public Result<ItemAttribute> remove(String userName, Integer id);
}    




