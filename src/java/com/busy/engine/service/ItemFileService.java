

































package com.busy.engine.service;

import com.busy.engine.entity.ItemFile;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ItemFileService
{
      public Result<ItemFile> find(String userName, Integer id);
      public Result<List<ItemFile>> findAll(String userName); 
      public Result<ItemFile> store(String userName, Integer itemFileId, String fileName, String description, String label, Integer hidden, Integer itemId);
      public Result<ItemFile> remove(String userName, Integer id);
}    




