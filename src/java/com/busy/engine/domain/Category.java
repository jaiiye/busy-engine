package com.busy.engine.domain;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Category extends AbstractEntity implements EntityItem<Integer>
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

    @Override
    public Integer getId()
    {

        return categoryId;
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(Category.PROP_CATEGORY_ID) || column.equals(Category.PROP_CATEGORY_NAME) || column.equals(Category.PROP_DISCOUNT_ID) || column.equals(Category.PROP_PARENT_ID))
        {
            return column;
        }
        else
        {
            throw new SQLException("Invalid column name: " + column);
        }
    }

    public static void checkColumnSize(String column, int size) throws Exception
    {
        if (column.length() > size)
        {
            throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
        }
    }

    public static boolean isColumnNumeric(String column)
    {
        if (column.equals(Category.PROP_CATEGORY_ID) || column.equals(Category.PROP_DISCOUNT_ID) || column.equals(Category.PROP_PARENT_ID))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Category process(ResultSet rs) throws SQLException
    {
        return new Category(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
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
