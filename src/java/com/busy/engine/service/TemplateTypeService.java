

































package com.busy.engine.service;

import com.busy.engine.entity.TemplateType;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface TemplateTypeService
{
      public Result<TemplateType> find(String userName, Integer id);
      public Result<List<TemplateType>> findAll(String userName); 
      public Result<TemplateType> store(String userName, Integer templateTypeId, String typeName, String typeValue);
      public Result<TemplateType> remove(String userName, Integer id);
}    




