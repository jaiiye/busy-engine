














package com.busy.engine.service;

import com.busy.engine.entity.EntityStatus;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface EntityStatusService
{
      public Result<EntityStatus> find(String userName, Integer id);
      public Result<List<EntityStatus>> findAll(String userName); 
      public Result<EntityStatus> store(String userName, Integer entityStatusId, Integer statusCode, String statusName, String appliesTo);
      public Result<EntityStatus> remove(String userName, Integer id);
}    








