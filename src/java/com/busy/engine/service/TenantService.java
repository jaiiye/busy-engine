

































package com.busy.engine.service;

import com.busy.engine.entity.Tenant;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface TenantService
{
      public Result<Tenant> find(String userName, Integer id);
      public Result<List<Tenant>> findAll(String userName); 
      public Result<Tenant> store(String userName, Integer tenantId, String name, String logo, Integer dashboardId);
      public Result<Tenant> remove(String userName, Integer id);
}    




