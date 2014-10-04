

































package com.busy.engine.service;

import com.busy.engine.entity.Template;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface TemplateService
{
      public Result<Template> find(String userName, Integer id);
      public Result<List<Template>> findAll(String userName); 
      public Result<Template> store(String userName, Integer templateId, String templateName, String markup, Integer templateStatus, Integer templateTypeId, Integer parentTemplateId);
      public Result<Template> remove(String userName, Integer id);
}    




