

































package com.busy.engine.service;

import com.busy.engine.entity.ItemImage;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ItemImageService
{
      public Result<ItemImage> find(String userName, Integer id);
      public Result<List<ItemImage>> findAll(String userName); 
      public Result<ItemImage> store(String userName, Integer itemImageId, String imageName, String thumbnailName, String alternateText, Integer rank, Integer itemId);
      public Result<ItemImage> remove(String userName, Integer id);
}    




