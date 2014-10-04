

































package com.busy.engine.service;

import com.busy.engine.entity.ItemOption;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ItemOptionService
{
      public Result<ItemOption> find(String userName, Integer id);
      public Result<List<ItemOption>> findAll(String userName); 
      public Result<ItemOption> store(String userName, Integer itemOptionId, String optionName, String description);
      public Result<ItemOption> remove(String userName, Integer id);
}    




