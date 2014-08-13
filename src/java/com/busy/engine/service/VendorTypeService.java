














package com.busy.engine.service;

import com.busy.engine.entity.VendorType;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface VendorTypeService
{
      public Result<VendorType> find(String userName, Integer id);
      public Result<List<VendorType>> findAll(String userName); 
      public Result<VendorType> store(String userName, Integer vendorTypeId, String typeName);
      public Result<VendorType> remove(String userName, Integer id);
}    








