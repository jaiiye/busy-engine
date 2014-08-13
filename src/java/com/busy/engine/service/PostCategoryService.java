














package com.busy.engine.service;

import com.busy.engine.entity.PostCategory;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface PostCategoryService
{
      public Result<PostCategory> find(String userName, Integer id);
      public Result<List<PostCategory>> findAll(String userName); 
      public Result<PostCategory> store(String userName, Integer postCategoryId, String categoryName);
      public Result<PostCategory> remove(String userName, Integer id);
}    








