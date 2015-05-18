package com.busy.engine.dao;

import com.busy.engine.entity.Item;

public interface ItemDao extends IGenericDao<Item, Integer>
{

        void getRelatedItemType(Item item);
        void getRelatedItemTypeWithInfo(Item item);        
        
        void getRelatedItemBrand(Item item);
        void getRelatedItemBrandWithInfo(Item item);        
        
        void getRelatedMetaTag(Item item);
        void getRelatedMetaTagWithInfo(Item item);        
        
        void getRelatedTemplate(Item item);
        void getRelatedTemplateWithInfo(Item item);        
        
        void getRelatedVendor(Item item);
        void getRelatedVendorWithInfo(Item item);        
         

    void getRelatedItemAttributeList(Item item);
    void getRelatedItemCategoryList(Item item);
    void getRelatedItemDiscountList(Item item);
    void getRelatedItemFileList(Item item);
    void getRelatedItemImageList(Item item);
    void getRelatedItemLocationList(Item item);
    void getRelatedItemReviewList(Item item);
    void getRelatedOptionAvailabilityList(Item item);
    void getRelatedOrderItemList(Item item);
    void getRelatedSiteItemList(Item item);
      
}
    
