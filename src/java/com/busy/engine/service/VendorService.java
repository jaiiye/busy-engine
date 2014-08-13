














package com.busy.engine.service;

import com.busy.engine.entity.Vendor;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface VendorService
{
      public Result<Vendor> find(String userName, Integer id);
      public Result<List<Vendor>> findAll(String userName); 
      public Result<Vendor> store(String userName, Integer vendorId, String vendorName, String description, Integer rank, Integer vendorStatus, Integer metaTagId, Integer templateId, Integer vendorTypeId);
      public Result<Vendor> remove(String userName, Integer id);
}    








