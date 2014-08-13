














package com.busy.engine.service;

import com.busy.engine.entity.Category;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface CategoryService
{
      public Result<Category> find(String userName, Integer id);
      public Result<List<Category>> findAll(String userName); 
      public Result<Category> store(String userName, Integer categoryId, String categoryName, Integer discountId, Integer parentCategoryId);
      public Result<Category> remove(String userName, Integer id);
}    








