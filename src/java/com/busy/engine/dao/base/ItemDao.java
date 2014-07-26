



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Item;

public interface ItemDao extends IGenericDao<Item, Integer>
{
                    
      Item getRelatedItemAttributeList(Item item);     
                  
      Item getRelatedItemCategoryList(Item item);     
                  
      Item getRelatedItemDiscountList(Item item);     
                  
      Item getRelatedItemFileList(Item item);     
                  
      Item getRelatedItemImageList(Item item);     
                  
      Item getRelatedItemLocationList(Item item);     
                  
      Item getRelatedItemReviewList(Item item);     
                  
      Item getRelatedOptionAvailabilityList(Item item);     
                  
      Item getRelatedOrderItemList(Item item);     
                  
      Item getRelatedSiteItemList(Item item);     
        
}
    

