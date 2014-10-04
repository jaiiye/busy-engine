

































package com.busy.engine.service;

import com.busy.engine.entity.Shipping;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ShippingService
{
      public Result<Shipping> find(String userName, Integer id);
      public Result<List<Shipping>> findAll(String userName); 
      public Result<Shipping> store(String userName, Integer shippingId, String methodName, Double quantity, String unitOfMeasure, Double ratePerUnitCost, Double additionalCost, Integer stateProvinceId, Integer countryId);
      public Result<Shipping> remove(String userName, Integer id);
}    




