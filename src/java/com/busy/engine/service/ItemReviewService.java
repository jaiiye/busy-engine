














package com.busy.engine.service;

import com.busy.engine.entity.ItemReview;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ItemReviewService
{
      public Result<ItemReview> find(String userName, Integer id);
      public Result<List<ItemReview>> findAll(String userName); 
      public Result<ItemReview> store(String userName, Integer itemReviewId, Integer itemId, Integer rating, Integer helpfulYes, Integer helpfulNo);
      public Result<ItemReview> remove(String userName, Integer id);
}    








