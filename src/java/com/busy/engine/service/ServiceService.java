














package com.busy.engine.service;

import com.busy.engine.entity.Service;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ServiceService
{
      public Result<Service> find(String userName, Integer id);
      public Result<List<Service>> findAll(String userName); 
      public Result<Service> store(String userName, Integer serviceId, String serviceName, String description, Integer serviceStatus, Integer serviceChargeId, Integer serviceTypeId);
      public Result<Service> remove(String userName, Integer id);
}    








