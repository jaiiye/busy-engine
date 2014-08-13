package com.busy.engine.entity;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.JsonObjectBuilder;
import java.text.SimpleDateFormat;

public class ServiceCharge extends AbstractEntity implements EntityItem<Integer>
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_SERVICE_CHARGE_ID = "ServiceChargeId";
    public static final String PROP_CHARGE_NAME = "ChargeName";
    public static final String PROP_DESCRIPTION = "Description";
    public static final String PROP_RATE = "Rate";
    public static final String PROP_UNITS = "Units";
    public static final String PROP_DATE = "Date";
    public static final String PROP_USER_SERVICE_ID = "UserServiceId";

    private Integer serviceChargeId;

    private String chargeName;

    private String description;

    private String rate;

    private String units;

    private Date date;

    private Integer userServiceId;
    private UserService userService;

    ArrayList<Service> serviceList;

    public ServiceCharge()
    {
        this.serviceChargeId = 0;
        this.chargeName = "";
        this.description = "";
        this.rate = "";
        this.units = "";
        this.date = null;
        this.userServiceId = 0;

        serviceList = null;

    }

    @Override
    public Integer getId()
    {

        return serviceChargeId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder)
    {
        builder.add("serviceChargeId", serviceChargeId).add("chargeName", chargeName).add("description", description).add("rate", rate).add("units", units).add("date", new SimpleDateFormat("yyyyMMdd").format(date)).add("userServiceId", userServiceId);
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(ServiceCharge.PROP_SERVICE_CHARGE_ID) || column.equals(ServiceCharge.PROP_CHARGE_NAME) || column.equals(ServiceCharge.PROP_DESCRIPTION) || column.equals(ServiceCharge.PROP_RATE) || column.equals(ServiceCharge.PROP_UNITS) || column.equals(ServiceCharge.PROP_DATE) || column.equals(ServiceCharge.PROP_USER_SERVICE_ID))
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
        if (column.equals(ServiceCharge.PROP_SERVICE_CHARGE_ID) || column.equals(ServiceCharge.PROP_USER_SERVICE_ID))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static ServiceCharge process(ResultSet rs) throws SQLException
    {
        return new ServiceCharge(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getInt(7));
    }

    public ServiceCharge(Integer ServiceChargeId, String ChargeName, String Description, String Rate, String Units, Date Date, Integer UserServiceId)
    {
        this.serviceChargeId = ServiceChargeId;
        this.chargeName = ChargeName;
        this.description = Description;
        this.rate = Rate;
        this.units = Units;
        this.date = Date;
        this.userServiceId = UserServiceId;

        serviceList = null;

    }

    public Integer getServiceChargeId()
    {
        return this.serviceChargeId;
    }

    public void setServiceChargeId(Integer ServiceChargeId)
    {
        this.serviceChargeId = ServiceChargeId;
    }

    public String getChargeName()
    {
        return this.chargeName;
    }

    public void setChargeName(String ChargeName)
    {
        this.chargeName = ChargeName;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String Description)
    {
        this.description = Description;
    }

    public String getRate()
    {
        return this.rate;
    }

    public void setRate(String Rate)
    {
        this.rate = Rate;
    }

    public String getUnits()
    {
        return this.units;
    }

    public void setUnits(String Units)
    {
        this.units = Units;
    }

    public Date getDate()
    {
        return this.date;
    }

    public void setDate(Date Date)
    {
        this.date = Date;
    }

    public Integer getUserServiceId()
    {
        return this.userServiceId;
    }

    public void setUserServiceId(Integer UserServiceId)
    {
        this.userServiceId = UserServiceId;
    }

    public UserService getUserService()
    {
        return this.userService;
    }

    public void setUserService(UserService userService)
    {
        this.userService = userService;
    }

    public ArrayList<Service> getServiceList()
    {
        return this.serviceList;
    }

    public void setServiceList(ArrayList<Service> serviceList)
    {
        this.serviceList = serviceList;
    }

}
