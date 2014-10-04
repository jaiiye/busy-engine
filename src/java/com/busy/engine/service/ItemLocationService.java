

































package com.busy.engine.service;

import com.busy.engine.entity.ItemLocation;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ItemLocationService
{
      public Result<ItemLocation> find(String userName, Integer id);
      public Result<List<ItemLocation>> findAll(String userName); 
      public Result<ItemLocation> store(String userName, Integer itemLocationId, String latitude, String longitude, Integer itemId, Integer addressId, Integer contactId);
      public Result<ItemLocation> remove(String userName, Integer id);
}    




