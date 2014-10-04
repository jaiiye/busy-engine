

































package com.busy.engine.service;

import com.busy.engine.entity.ImageType;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ImageTypeService
{
      public Result<ImageType> find(String userName, Integer id);
      public Result<List<ImageType>> findAll(String userName); 
      public Result<ImageType> store(String userName, Integer imageTypeId, String typeName, String description);
      public Result<ImageType> remove(String userName, Integer id);
}    




