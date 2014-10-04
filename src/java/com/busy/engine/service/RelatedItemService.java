

































package com.busy.engine.service;

import com.busy.engine.entity.RelatedItem;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface RelatedItemService
{
      public Result<RelatedItem> find(String userName, Integer id);
      public Result<List<RelatedItem>> findAll(String userName); 
      public Result<RelatedItem> store(String userName, Integer relatedItemId, Integer item1, Integer item2);
      public Result<RelatedItem> remove(String userName, Integer id);
}    




