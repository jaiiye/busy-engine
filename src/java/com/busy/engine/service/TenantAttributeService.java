

































package com.busy.engine.service;

import com.busy.engine.entity.TenantAttribute;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface TenantAttributeService
{
      public Result<TenantAttribute> find(String userName, Integer id);
      public Result<List<TenantAttribute>> findAll(String userName); 
      public Result<TenantAttribute> store(String userName, Integer tenantAttributeId, String attributeKey, String attributeValue, Integer tenantId);
      public Result<TenantAttribute> remove(String userName, Integer id);
}    




