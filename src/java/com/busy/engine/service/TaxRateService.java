

































package com.busy.engine.service;

import com.busy.engine.entity.TaxRate;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface TaxRateService
{
      public Result<TaxRate> find(String userName, Integer id);
      public Result<List<TaxRate>> findAll(String userName); 
      public Result<TaxRate> store(String userName, Integer taxRateId, String taxCategory, Double percentage, String zipPostalCode, Integer stateProvinceId, Integer countryId);
      public Result<TaxRate> remove(String userName, Integer id);
}    




