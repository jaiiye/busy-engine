

































package com.busy.engine.service;

import com.busy.engine.entity.SiteEmail;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface SiteEmailService
{
      public Result<SiteEmail> find(String userName, Integer id);
      public Result<List<SiteEmail>> findAll(String userName); 
      public Result<SiteEmail> store(String userName, Integer siteEmailId, String host, Integer port, String username, String password);
      public Result<SiteEmail> remove(String userName, Integer id);
}    




