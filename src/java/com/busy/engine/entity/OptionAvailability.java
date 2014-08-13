package com.busy.engine.entity;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.JsonObjectBuilder;
import java.text.SimpleDateFormat;

public class OptionAvailability extends AbstractEntity implements EntityItem<Integer>
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_OPTION_AVAILABILITY_ID = "OptionAvailabilityId";
    public static final String PROP_ITEM_ID = "ItemId";
    public static final String PROP_ITEM_OPTION_ID = "ItemOptionId";
    public static final String PROP_ITEM_AVAILABILITY_ID = "ItemAvailabilityId";
    public static final String PROP_AVAILABLE_QUANTITY = "AvailableQuantity";
    public static final String PROP_PRICE = "Price";
    public static final String PROP_AVAILABLE_FROM = "AvailableFrom";
    public static final String PROP_AVAILABLE_TO = "AvailableTo";
    public static final String PROP_MAXIMUM_QUANTITY = "MaximumQuantity";

    private Integer optionAvailabilityId;

    private Integer itemId;
    private Item item;
    private Integer itemOptionId;
    private ItemOption itemOption;
    private Integer itemAvailabilityId;
    private ItemAvailability itemAvailability;
    private Integer availableQuantity;

    private Double price;

    private Date availableFrom;

    private Date availableTo;

    private Integer maximumQuantity;

    public OptionAvailability()
    {
        this.optionAvailabilityId = 0;
        this.itemId = 0;
        this.itemOptionId = 0;
        this.itemAvailabilityId = 0;
        this.availableQuantity = 0;
        this.price = 0.0;
        this.availableFrom = null;
        this.availableTo = null;
        this.maximumQuantity = 0;

    }

    @Override
    public Integer getId()
    {

        return optionAvailabilityId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder)
    {
        builder.add("optionAvailabilityId", optionAvailabilityId).add("itemId", itemId).add("itemOptionId", itemOptionId).add("itemAvailabilityId", itemAvailabilityId).add("availableQuantity", availableQuantity).add("price", price).add("availableFrom", new SimpleDateFormat("yyyyMMdd").format(availableFrom)).add("availableTo", new SimpleDateFormat("yyyyMMdd").format(availableTo)).add("maximumQuantity", maximumQuantity);
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(OptionAvailability.PROP_OPTION_AVAILABILITY_ID) || column.equals(OptionAvailability.PROP_ITEM_ID) || column.equals(OptionAvailability.PROP_ITEM_OPTION_ID) || column.equals(OptionAvailability.PROP_ITEM_AVAILABILITY_ID) || column.equals(OptionAvailability.PROP_AVAILABLE_QUANTITY) || column.equals(OptionAvailability.PROP_PRICE) || column.equals(OptionAvailability.PROP_AVAILABLE_FROM) || column.equals(OptionAvailability.PROP_AVAILABLE_TO) || column.equals(OptionAvailability.PROP_MAXIMUM_QUANTITY))
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
        if (column.equals(OptionAvailability.PROP_OPTION_AVAILABILITY_ID) || column.equals(OptionAvailability.PROP_ITEM_ID) || column.equals(OptionAvailability.PROP_ITEM_OPTION_ID) || column.equals(OptionAvailability.PROP_ITEM_AVAILABILITY_ID) || column.equals(OptionAvailability.PROP_AVAILABLE_QUANTITY) || column.equals(OptionAvailability.PROP_PRICE) || column.equals(OptionAvailability.PROP_MAXIMUM_QUANTITY))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static OptionAvailability process(ResultSet rs) throws SQLException
    {
        return new OptionAvailability(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getDate(7), rs.getDate(8), rs.getInt(9));
    }

    public OptionAvailability(Integer OptionAvailabilityId, Integer ItemId, Integer ItemOptionId, Integer ItemAvailabilityId, Integer AvailableQuantity, Double Price, Date AvailableFrom, Date AvailableTo, Integer MaximumQuantity)
    {
        this.optionAvailabilityId = OptionAvailabilityId;
        this.itemId = ItemId;
        this.itemOptionId = ItemOptionId;
        this.itemAvailabilityId = ItemAvailabilityId;
        this.availableQuantity = AvailableQuantity;
        this.price = Price;
        this.availableFrom = AvailableFrom;
        this.availableTo = AvailableTo;
        this.maximumQuantity = MaximumQuantity;

    }

    public Integer getOptionAvailabilityId()
    {
        return this.optionAvailabilityId;
    }

    public void setOptionAvailabilityId(Integer OptionAvailabilityId)
    {
        this.optionAvailabilityId = OptionAvailabilityId;
    }

    public Integer getItemId()
    {
        return this.itemId;
    }

    public void setItemId(Integer ItemId)
    {
        this.itemId = ItemId;
    }

    public Item getItem()
    {
        return this.item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public Integer getItemOptionId()
    {
        return this.itemOptionId;
    }

    public void setItemOptionId(Integer ItemOptionId)
    {
        this.itemOptionId = ItemOptionId;
    }

    public ItemOption getItemOption()
    {
        return this.itemOption;
    }

    public void setItemOption(ItemOption itemOption)
    {
        this.itemOption = itemOption;
    }

    public Integer getItemAvailabilityId()
    {
        return this.itemAvailabilityId;
    }

    public void setItemAvailabilityId(Integer ItemAvailabilityId)
    {
        this.itemAvailabilityId = ItemAvailabilityId;
    }

    public ItemAvailability getItemAvailability()
    {
        return this.itemAvailability;
    }

    public void setItemAvailability(ItemAvailability itemAvailability)
    {
        this.itemAvailability = itemAvailability;
    }

    public Integer getAvailableQuantity()
    {
        return this.availableQuantity;
    }

    public void setAvailableQuantity(Integer AvailableQuantity)
    {
        this.availableQuantity = AvailableQuantity;
    }

    public Double getPrice()
    {
        return this.price;
    }

    public void setPrice(Double Price)
    {
        this.price = Price;
    }

    public Date getAvailableFrom()
    {
        return this.availableFrom;
    }

    public void setAvailableFrom(Date AvailableFrom)
    {
        this.availableFrom = AvailableFrom;
    }

    public Date getAvailableTo()
    {
        return this.availableTo;
    }

    public void setAvailableTo(Date AvailableTo)
    {
        this.availableTo = AvailableTo;
    }

    public Integer getMaximumQuantity()
    {
        return this.maximumQuantity;
    }

    public void setMaximumQuantity(Integer MaximumQuantity)
    {
        this.maximumQuantity = MaximumQuantity;
    }

}
