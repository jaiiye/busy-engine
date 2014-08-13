














package com.busy.engine.service;

import com.busy.engine.entity.Blog;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface BlogService
{
      public Result<Blog> find(String userName, Integer id);
      public Result<List<Blog>> findAll(String userName); 
      public Result<Blog> store(String userName, Integer blogId, String topic, Integer blogTypeId, Integer knowledgeBaseId);
      public Result<Blog> remove(String userName, Integer id);
}    








