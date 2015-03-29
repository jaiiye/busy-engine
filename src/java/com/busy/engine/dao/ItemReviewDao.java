


































package com.busy.engine.dao;

import com.busy.engine.entity.ItemReview;

public interface ItemReviewDao extends IGenericDao<ItemReview, Integer>
{

        void getRelatedItem(ItemReview item_review);
        void getRelatedItemWithInfo(ItemReview item_review);        
         

    void getRelatedCommentList(ItemReview item_review);
      
}
    
