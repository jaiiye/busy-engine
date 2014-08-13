














package com.busy.engine.service;

import com.busy.engine.entity.UserGroup;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface UserGroupService
{
      public Result<UserGroup> find(String userName, Integer id);
      public Result<List<UserGroup>> findAll(String userName); 
      public Result<UserGroup> store(String userName, Integer userGroupId, String groupName, Integer siteId, Integer discountId);
      public Result<UserGroup> remove(String userName, Integer id);
}    








