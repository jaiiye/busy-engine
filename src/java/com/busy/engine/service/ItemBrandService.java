














package com.busy.engine.service;

import com.busy.engine.entity.ItemBrand;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ItemBrandService
{
      public Result<ItemBrand> find(String userName, Integer id);
      public Result<List<ItemBrand>> findAll(String userName); 
      public Result<ItemBrand> store(String userName, Integer itemBrandId, String brandName, String description);
      public Result<ItemBrand> remove(String userName, Integer id);
}    








