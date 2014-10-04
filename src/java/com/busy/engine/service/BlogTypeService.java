

































package com.busy.engine.service;

import com.busy.engine.entity.BlogType;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface BlogTypeService
{
      public Result<BlogType> find(String userName, Integer id);
      public Result<List<BlogType>> findAll(String userName); 
      public Result<BlogType> store(String userName, Integer blogTypeId, String typeName);
      public Result<BlogType> remove(String userName, Integer id);
}    




