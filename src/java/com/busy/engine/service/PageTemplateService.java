














package com.busy.engine.service;

import com.busy.engine.entity.PageTemplate;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface PageTemplateService
{
      public Result<PageTemplate> find(String userName, Integer id);
      public Result<List<PageTemplate>> findAll(String userName); 
      public Result<PageTemplate> store(String userName, Integer pageTemplateId, String name, String markup);
      public Result<PageTemplate> remove(String userName, Integer id);
}    








