

































package com.busy.engine.service;

import com.busy.engine.entity.UserActionType;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface UserActionTypeService
{
      public Result<UserActionType> find(String userName, Integer id);
      public Result<List<UserActionType>> findAll(String userName); 
      public Result<UserActionType> store(String userName, Integer userActionTypeId, String typeName);
      public Result<UserActionType> remove(String userName, Integer id);
}    




