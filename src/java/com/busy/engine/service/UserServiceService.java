














package com.busy.engine.service;

import com.busy.engine.entity.UserService;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface UserServiceService
{
      public Result<UserService> find(String userName, Integer id);
      public Result<List<UserService>> findAll(String userName); 
      public Result<UserService> store(String userName, Integer userServiceId, Date startDate, Date endDate, String details, String contractUrl, String deliverableUrl, Double depositAmount, Integer userRank, Integer blogId, Integer userId, Integer serviceId);
      public Result<UserService> remove(String userName, Integer id);
}    








