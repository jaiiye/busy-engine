

































package com.busy.engine.service;

import com.busy.engine.entity.OptionAvailability;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface OptionAvailabilityService
{
      public Result<OptionAvailability> find(String userName, Integer id);
      public Result<List<OptionAvailability>> findAll(String userName); 
      public Result<OptionAvailability> store(String userName, Integer optionAvailabilityId, Integer itemId, Integer itemOptionId, Integer itemAvailabilityId, Integer availableQuantity, Double price, Date availableFrom, Date availableTo, Integer maximumQuantity);
      public Result<OptionAvailability> remove(String userName, Integer id);
}    




