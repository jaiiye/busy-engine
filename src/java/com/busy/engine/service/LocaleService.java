

































package com.busy.engine.service;

import com.busy.engine.entity.Locale;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface LocaleService
{
      public Result<Locale> find(String userName, Integer id);
      public Result<List<Locale>> findAll(String userName); 
      public Result<Locale> store(String userName, Integer localeId, String localeString, String localeCharacterSet);
      public Result<Locale> remove(String userName, Integer id);
}    




