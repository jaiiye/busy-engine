package com.busy.engine.entity;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.JsonObjectBuilder;
import java.text.SimpleDateFormat;

public class Discount extends AbstractEntity implements EntityItem<Integer>
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_DISCOUNT_ID = "DiscountId";
    public static final String PROP_DISCOUNT_NAME = "DiscountName";
    public static final String PROP_DISCOUNT_AMOUNT = "DiscountAmount";
    public static final String PROP_DISCOUNT_PERCENT = "DiscountPercent";
    public static final String PROP_START_DATE = "StartDate";
    public static final String PROP_END_DATE = "EndDate";
    public static final String PROP_COUPON_CODE = "CouponCode";
    public static final String PROP_DISCOUNT_STATUS = "DiscountStatus";
    public static final String PROP_ASK_COUPON_CODE = "AskCouponCode";
    public static final String PROP_USE_PERCENTAGE = "UsePercentage";

    private Integer discountId;

    private String discountName;

    private Double discountAmount;

    private Double discountPercent;

    private Date startDate;

    private Date endDate;

    private String couponCode;

    private Integer discountStatus;

    private Integer askCouponCode;

    private Integer usePercentage;

    ArrayList<Category> categoryList;
    ArrayList<CustomerOrder> customerOrderList;
    ArrayList<ItemDiscount> itemDiscountList;
    ArrayList<UserGroup> userGroupList;

    public Discount()
    {
        this.discountId = 0;
        this.discountName = "";
        this.discountAmount = 0.0;
        this.discountPercent = 0.0;
        this.startDate = null;
        this.endDate = null;
        this.couponCode = "";
        this.discountStatus = 0;
        this.askCouponCode = 0;
        this.usePercentage = 0;

        categoryList = null;
        customerOrderList = null;
        itemDiscountList = null;
        userGroupList = null;

    }

    @Override
    public Integer getId()
    {

        return discountId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder)
    {
        builder.add("discountId", discountId).add("discountName", discountName).add("discountAmount", discountAmount).add("discountPercent", discountPercent).add("startDate", new SimpleDateFormat("yyyyMMdd").format(startDate)).add("endDate", new SimpleDateFormat("yyyyMMdd").format(endDate)).add("couponCode", couponCode).add("discountStatus", discountStatus).add("askCouponCode", askCouponCode).add("usePercentage", usePercentage);
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(Discount.PROP_DISCOUNT_ID) || column.equals(Discount.PROP_DISCOUNT_NAME) || column.equals(Discount.PROP_DISCOUNT_AMOUNT) || column.equals(Discount.PROP_DISCOUNT_PERCENT) || column.equals(Discount.PROP_START_DATE) || column.equals(Discount.PROP_END_DATE) || column.equals(Discount.PROP_COUPON_CODE) || column.equals(Discount.PROP_DISCOUNT_STATUS) || column.equals(Discount.PROP_ASK_COUPON_CODE) || column.equals(Discount.PROP_USE_PERCENTAGE))
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
        if (column.equals(Discount.PROP_DISCOUNT_ID) || column.equals(Discount.PROP_DISCOUNT_AMOUNT) || column.equals(Discount.PROP_DISCOUNT_PERCENT) || column.equals(Discount.PROP_DISCOUNT_STATUS) || column.equals(Discount.PROP_ASK_COUPON_CODE) || column.equals(Discount.PROP_USE_PERCENTAGE))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Discount process(ResultSet rs) throws SQLException
    {
        return new Discount(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
    }

    public Discount(Integer DiscountId, String DiscountName, Double DiscountAmount, Double DiscountPercent, Date StartDate, Date EndDate, String CouponCode, Integer DiscountStatus, Integer AskCouponCode, Integer UsePercentage)
    {
        this.discountId = DiscountId;
        this.discountName = DiscountName;
        this.discountAmount = DiscountAmount;
        this.discountPercent = DiscountPercent;
        this.startDate = StartDate;
        this.endDate = EndDate;
        this.couponCode = CouponCode;
        this.discountStatus = DiscountStatus;
        this.askCouponCode = AskCouponCode;
        this.usePercentage = UsePercentage;

        categoryList = null;
        customerOrderList = null;
        itemDiscountList = null;
        userGroupList = null;

    }

    public Integer getDiscountId()
    {
        return this.discountId;
    }

    public void setDiscountId(Integer DiscountId)
    {
        this.discountId = DiscountId;
    }

    public String getDiscountName()
    {
        return this.discountName;
    }

    public void setDiscountName(String DiscountName)
    {
        this.discountName = DiscountName;
    }

    public Double getDiscountAmount()
    {
        return this.discountAmount;
    }

    public void setDiscountAmount(Double DiscountAmount)
    {
        this.discountAmount = DiscountAmount;
    }

    public Double getDiscountPercent()
    {
        return this.discountPercent;
    }

    public void setDiscountPercent(Double DiscountPercent)
    {
        this.discountPercent = DiscountPercent;
    }

    public Date getStartDate()
    {
        return this.startDate;
    }

    public void setStartDate(Date StartDate)
    {
        this.startDate = StartDate;
    }

    public Date getEndDate()
    {
        return this.endDate;
    }

    public void setEndDate(Date EndDate)
    {
        this.endDate = EndDate;
    }

    public String getCouponCode()
    {
        return this.couponCode;
    }

    public void setCouponCode(String CouponCode)
    {
        this.couponCode = CouponCode;
    }

    public Integer getDiscountStatus()
    {
        return this.discountStatus;
    }

    public void setDiscountStatus(Integer DiscountStatus)
    {
        this.discountStatus = DiscountStatus;
    }

    public Integer getAskCouponCode()
    {
        return this.askCouponCode;
    }

    public void setAskCouponCode(Integer AskCouponCode)
    {
        this.askCouponCode = AskCouponCode;
    }

    public Integer getUsePercentage()
    {
        return this.usePercentage;
    }

    public void setUsePercentage(Integer UsePercentage)
    {
        this.usePercentage = UsePercentage;
    }

    public ArrayList<Category> getCategoryList()
    {
        return this.categoryList;
    }

    public void setCategoryList(ArrayList<Category> categoryList)
    {
        this.categoryList = categoryList;
    }

    public ArrayList<CustomerOrder> getCustomerOrderList()
    {
        return this.customerOrderList;
    }

    public void setCustomerOrderList(ArrayList<CustomerOrder> customerOrderList)
    {
        this.customerOrderList = customerOrderList;
    }

    public ArrayList<ItemDiscount> getItemDiscountList()
    {
        return this.itemDiscountList;
    }

    public void setItemDiscountList(ArrayList<ItemDiscount> itemDiscountList)
    {
        this.itemDiscountList = itemDiscountList;
    }

    public ArrayList<UserGroup> getUserGroupList()
    {
        return this.userGroupList;
    }

    public void setUserGroupList(ArrayList<UserGroup> userGroupList)
    {
        this.userGroupList = userGroupList;
    }

}
