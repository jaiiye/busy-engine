














package com.busy.engine.service;

import com.busy.engine.entity.FormFieldType;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface FormFieldTypeService
{
      public Result<FormFieldType> find(String userName, Integer id);
      public Result<List<FormFieldType>> findAll(String userName); 
      public Result<FormFieldType> store(String userName, Integer formFieldTypeId, String typeName, String inputType);
      public Result<FormFieldType> remove(String userName, Integer id);
}    








