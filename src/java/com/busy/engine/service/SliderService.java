














package com.busy.engine.service;

import com.busy.engine.entity.Slider;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface SliderService
{
      public Result<Slider> find(String userName, Integer id);
      public Result<List<Slider>> findAll(String userName); 
      public Result<Slider> store(String userName, Integer sliderId, String sliderName, Integer sliderTypeId, Integer formId);
      public Result<Slider> remove(String userName, Integer id);
}    








