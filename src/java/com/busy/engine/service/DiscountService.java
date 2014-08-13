














package com.busy.engine.service;

import com.busy.engine.entity.Discount;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface DiscountService
{
      public Result<Discount> find(String userName, Integer id);
      public Result<List<Discount>> findAll(String userName); 
      public Result<Discount> store(String userName, Integer discountId, String discountName, Double discountAmount, Double discountPercent, Date startDate, Date endDate, String couponCode, Integer discountStatus, Integer askCouponCode, Integer usePercentage);
      public Result<Discount> remove(String userName, Integer id);
}    








