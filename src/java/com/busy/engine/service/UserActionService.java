














package com.busy.engine.service;

import com.busy.engine.entity.UserAction;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface UserActionService
{
      public Result<UserAction> find(String userName, Integer id);
      public Result<List<UserAction>> findAll(String userName); 
      public Result<UserAction> store(String userName, Integer userActionId, Date date, String detail, Integer userActionTypeId, Integer userId);
      public Result<UserAction> remove(String userName, Integer id);
}    








