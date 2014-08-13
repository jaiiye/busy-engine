














package com.busy.engine.service;

import com.busy.engine.entity.Affiliate;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface AffiliateService
{
      public Result<Affiliate> find(String userName, Integer id);
      public Result<List<Affiliate>> findAll(String userName); 
      public Result<Affiliate> store(String userName, Integer affiliateId, String companyName, String email, String phone, String fax, String webUrl, String details, Integer serviceHours, Integer affiliateStatus, Integer userId, Integer contactId, Integer addressId);
      public Result<Affiliate> remove(String userName, Integer id);
}    








