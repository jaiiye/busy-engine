

































package com.busy.engine.service;

import com.busy.engine.entity.StateProvince;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface StateProvinceService
{
      public Result<StateProvince> find(String userName, Integer id);
      public Result<List<StateProvince>> findAll(String userName); 
      public Result<StateProvince> store(String userName, Integer stateProvinceId, String name, String abbreviation, Integer countryId);
      public Result<StateProvince> remove(String userName, Integer id);
}    




