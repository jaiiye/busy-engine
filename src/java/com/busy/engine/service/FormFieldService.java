














package com.busy.engine.service;

import com.busy.engine.entity.FormField;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface FormFieldService
{
      public Result<FormField> find(String userName, Integer id);
      public Result<List<FormField>> findAll(String userName); 
      public Result<FormField> store(String userName, Integer formFieldId, String fieldName, String label, String errorText, String validationRegex, Integer rank, String defaultValue, String options, String groupName, Integer optional, Integer formFieldTypeId, Integer formId);
      public Result<FormField> remove(String userName, Integer id);
}    








