

































package com.busy.engine.service;

import com.busy.engine.entity.ItemAvailability;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ItemAvailabilityService
{
      public Result<ItemAvailability> find(String userName, Integer id);
      public Result<List<ItemAvailability>> findAll(String userName); 
      public Result<ItemAvailability> store(String userName, Integer itemAvailabilityId, String type);
      public Result<ItemAvailability> remove(String userName, Integer id);
}    




