


































package com.busy.engine.dao;

import com.busy.engine.entity.ItemReview;

public interface ItemReviewDao extends IGenericDao<ItemReview, Integer>
{
                    
      void getRelatedCommentList(ItemReview item_review);     
        
}
    

