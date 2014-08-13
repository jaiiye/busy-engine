














package com.busy.engine.service;

import com.busy.engine.entity.BlogPostCategory;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface BlogPostCategoryService
{
      public Result<BlogPostCategory> find(String userName, Integer id);
      public Result<List<BlogPostCategory>> findAll(String userName); 
      public Result<BlogPostCategory> store(String userName, Integer blogPostCategoryId, Integer blogPostId, Integer postCategoryId);
      public Result<BlogPostCategory> remove(String userName, Integer id);
}    








