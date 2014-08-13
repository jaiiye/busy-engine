














package com.busy.engine.service;

import com.busy.engine.entity.Country;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface CountryService
{
      public Result<Country> find(String userName, Integer id);
      public Result<List<Country>> findAll(String userName); 
      public Result<Country> store(String userName, Integer countryId, String name, String isoCode, Integer isoNumber, Integer hasVat);
      public Result<Country> remove(String userName, Integer id);
}    








