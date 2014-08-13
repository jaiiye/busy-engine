














package com.busy.engine.service;

import com.busy.engine.entity.RecurringPayment;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface RecurringPaymentService
{
      public Result<RecurringPayment> find(String userName, Integer id);
      public Result<List<RecurringPayment>> findAll(String userName); 
      public Result<RecurringPayment> store(String userName, Integer recurringPaymentId, Integer cycleLength, Integer cyclePeriod, Integer totalCycles, Date startDate, Integer orderId);
      public Result<RecurringPayment> remove(String userName, Integer id);
}    








