














package com.busy.engine.service;

import com.busy.engine.entity.Page;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface PageService
{
      public Result<Page> find(String userName, Integer id);
      public Result<List<Page>> findAll(String userName); 
      public Result<Page> store(String userName, Integer pageId, String pageName, String content, Integer pageStatus, Integer formId, Integer sliderId, Integer metaTagId, Integer templateId);
      public Result<Page> remove(String userName, Integer id);
}    








