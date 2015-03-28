package com.busy.engine.dao;

import com.busy.engine.entity.Item;

public interface ItemDao extends IGenericDao<Item, Integer>
{

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
