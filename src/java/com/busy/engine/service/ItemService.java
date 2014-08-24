package com.busy.engine.service;

import com.busy.engine.entity.Item;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface ItemService
{
    public Result<Item> find(String userName, Integer id);
    public Result<List<Item>> findAll(String userName);
    public Result<Item> store(String userName, Integer itemId, String itemName, String description, Double listPrice, Double price, String shortDescription, Integer adjustment, String sku, Integer ratingSum, Integer voteCount, Integer rank, Integer itemStatus, String locale, Integer itemTypeId, Integer itemBrandId, Integer metaTagId, Integer templateId, Integer vendorId);
    public Result<Item> remove(String userName, Integer id);
}
