

































package com.busy.engine.service;

import com.busy.engine.entity.ServiceType;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ServiceTypeService
{
      public Result<ServiceType> find(String userName, Integer id);
      public Result<List<ServiceType>> findAll(String userName); 
      public Result<ServiceType> store(String userName, Integer serviceTypeId, String typeName, String desciption);
      public Result<ServiceType> remove(String userName, Integer id);
}    




