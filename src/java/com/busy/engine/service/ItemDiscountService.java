

































package com.busy.engine.service;

import com.busy.engine.entity.ItemDiscount;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ItemDiscountService
{
      public Result<ItemDiscount> find(String userName, Integer id);
      public Result<List<ItemDiscount>> findAll(String userName); 
      public Result<ItemDiscount> store(String userName, Integer itemDiscountId, Integer itemId, Integer discountId, Integer applyToOptions);
      public Result<ItemDiscount> remove(String userName, Integer id);
}    




