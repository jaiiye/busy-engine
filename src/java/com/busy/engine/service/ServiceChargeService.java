














package com.busy.engine.service;

import com.busy.engine.entity.ServiceCharge;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ServiceChargeService
{
      public Result<ServiceCharge> find(String userName, Integer id);
      public Result<List<ServiceCharge>> findAll(String userName); 
      public Result<ServiceCharge> store(String userName, Integer serviceChargeId, String chargeName, String description, String rate, String units, Date date, Integer userServiceId);
      public Result<ServiceCharge> remove(String userName, Integer id);
}    








