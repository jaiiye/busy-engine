














package com.busy.engine.service;

import com.busy.engine.entity.UserType;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface UserTypeService
{
      public Result<UserType> find(String userName, Integer id);
      public Result<List<UserType>> findAll(String userName); 
      public Result<UserType> store(String userName, Integer userTypeId, String typeName, String description, String redirectUrl);
      public Result<UserType> remove(String userName, Integer id);
}    








