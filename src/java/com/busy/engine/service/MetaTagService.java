

































package com.busy.engine.service;

import com.busy.engine.entity.MetaTag;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface MetaTagService
{
      public Result<MetaTag> find(String userName, Integer id);
      public Result<List<MetaTag>> findAll(String userName); 
      public Result<MetaTag> store(String userName, Integer metaTagId, String title, String description, String keywords);
      public Result<MetaTag> remove(String userName, Integer id);
}    




