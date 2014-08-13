














package com.busy.engine.service;

import com.busy.engine.entity.OrderItem;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface OrderItemService
{
      public Result<OrderItem> find(String userName, Integer id);
      public Result<List<OrderItem>> findAll(String userName); 
      public Result<OrderItem> store(String userName, Integer orderItemId, Integer customerOrderId, Integer itemId, Integer quantity, String optionName, Double unitPrice);
      public Result<OrderItem> remove(String userName, Integer id);
}    








