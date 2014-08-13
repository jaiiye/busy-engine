














package com.busy.engine.service;

import com.busy.engine.entity.SiteLanguage;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface SiteLanguageService
{
      public Result<SiteLanguage> find(String userName, Integer id);
      public Result<List<SiteLanguage>> findAll(String userName); 
      public Result<SiteLanguage> store(String userName, Integer siteLanguageId, String languageName, String locale, Integer rtl, String flagFileName, Integer siteId);
      public Result<SiteLanguage> remove(String userName, Integer id);
}    








