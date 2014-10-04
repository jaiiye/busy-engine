

































package com.busy.engine.service;

import com.busy.engine.entity.Dashboard;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface DashboardService
{
      public Result<Dashboard> find(String userName, Integer id);
      public Result<List<Dashboard>> findAll(String userName); 
      public Result<Dashboard> store(String userName, Integer dashboardId, Integer userCount, Integer blogPostCount, Integer itemCount, Integer orderCount, Integer siteFileCount, Integer imageCount, Integer blogCount, Integer commentCount, Integer pageCount, Integer formCount, Integer sliderCount, Integer itemBrandCount, Integer categoryCount, Integer itemOptionCount);
      public Result<Dashboard> remove(String userName, Integer id);
}    




