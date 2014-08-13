














package com.busy.engine.service;

import com.busy.engine.entity.CustomerOrder;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface CustomerOrderService
{
      public Result<CustomerOrder> find(String userName, Integer id);
      public Result<List<CustomerOrder>> findAll(String userName); 
      public Result<CustomerOrder> store(String userName, Integer customerOrderId, Integer customerId, Integer orderId, Integer discountId, String customerIp);
      public Result<CustomerOrder> remove(String userName, Integer id);
}    








