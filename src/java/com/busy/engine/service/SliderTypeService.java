














package com.busy.engine.service;

import com.busy.engine.entity.SliderType;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface SliderTypeService
{
      public Result<SliderType> find(String userName, Integer id);
      public Result<List<SliderType>> findAll(String userName); 
      public Result<SliderType> store(String userName, Integer sliderTypeId, String typeName, String code);
      public Result<SliderType> remove(String userName, Integer id);
}    








