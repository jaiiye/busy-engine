

































package com.busy.engine.service;

import com.busy.engine.entity.SliderItem;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface SliderItemService
{
      public Result<SliderItem> find(String userName, Integer id);
      public Result<List<SliderItem>> findAll(String userName); 
      public Result<SliderItem> store(String userName, Integer sliderItemId, String title, String description, String url, String imageName, String alternateText, Integer rank, Integer sliderId);
      public Result<SliderItem> remove(String userName, Integer id);
}    




