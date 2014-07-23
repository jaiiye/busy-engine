package com.busy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Category implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_CATEGORY_ID = "CategoryId";
    public static final String PROP_CATEGORY_NAME = "CategoryName";
    public static final String PROP_DISCOUNT_ID = "DiscountId";
    public static final String PROP_PARENT_ID = "ParentId";

    private Integer categoryId;

    private String categoryName;

    private Integer discountId;
    private Discount discount;
    private Integer parentId;
    private Category parent;

    ArrayList<ItemCategory> itemCategoryList;

    public Category()
    {
        this.categoryId = 0;
        this.categoryName = "";
        this.discountId = 0;
        this.parentId = 0;

        itemCategoryList = null;

    }

    public Category(Integer CategoryId, String CategoryName, Integer DiscountId, Integer ParentId)
    {
        this.categoryId = CategoryId;
        this.categoryName = CategoryName;
        this.discountId = DiscountId;
        this.parentId = ParentId;

        itemCategoryList = null;

    }

    public Integer getCategoryId()
    {
        return this.categoryId;
    }

    public void setCategoryId(Integer CategoryId)
    {
        this.categoryId = CategoryId;
    }

    public String getCategoryName()
    {
        return this.categoryName;
    }

    public void setCategoryName(String CategoryName)
    {
        this.categoryName = CategoryName;
    }

    public Integer getDiscountId()
    {
        return this.discountId;
    }

    public void setDiscountId(Integer DiscountId)
    {
        this.discountId = DiscountId;
    }

    public Discount getDiscount()
    {
        return this.discount;
    }

    public void setDiscount(Discount discount)
    {
        this.discount = discount;
    }

    public Integer getParentId()
    {
        return this.parentId;
    }

    public void setParentId(Integer ParentId)
    {
        this.parentId = ParentId;
    }

    public Category getParent()
    {
        return this.parent;
    }

    public void setParent(Category parent)
    {
        this.parent = parent;
    }

    public ArrayList<ItemCategory> getItemCategoryList()
    {
        return this.itemCategoryList;
    }

    public void setItemCategoryList(ArrayList<ItemCategory> itemCategoryList)
    {
        this.itemCategoryList = itemCategoryList;
    }

}
