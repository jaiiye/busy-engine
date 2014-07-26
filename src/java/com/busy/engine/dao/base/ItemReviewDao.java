



















package com.busy.engine.dao.base;

import com.busy.engine.domain.ItemReview;

public interface ItemReviewDao extends IGenericDao<ItemReview, Integer>
{
                    
      ItemReview getRelatedCommentList(ItemReview item_review);     
        
}
    

