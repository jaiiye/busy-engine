

































package com.busy.engine.service;

import com.busy.engine.entity.Form;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface FormService
{
      public Result<Form> find(String userName, Integer id);
      public Result<List<Form>> findAll(String userName); 
      public Result<Form> store(String userName, Integer formId, String formName, String description, String submissionEmail, String submissionMethod, String action, Integer resettable, Integer fileUpload);
      public Result<Form> remove(String userName, Integer id);
}    




