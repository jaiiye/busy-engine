package com.busy.engine.entity;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.JsonObjectBuilder;
import java.text.SimpleDateFormat;

public class TenantAttribute extends AbstractEntity implements EntityItem<Integer>
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_TENANT_ATTRIBUTE_ID = "TenantAttributeId";
    public static final String PROP_ATTRIBUTE_KEY = "AttributeKey";
    public static final String PROP_ATTRIBUTE_VALUE = "AttributeValue";
    public static final String PROP_TENANT_ID = "TenantId";

    private Integer tenantAttributeId;
    private String attributeKey;
    private String attributeValue;
    private Integer tenantId;
    private Tenant tenant;

    public TenantAttribute()
    {
        this.tenantAttributeId = 0;
        this.attributeKey = "";
        this.attributeValue = "";
        this.tenantId = 0;
    }

    @Override
    public Integer getId()
    {
        return tenantAttributeId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder)
    {
        builder.add("tenantAttributeId", tenantAttributeId == null ? 0 : tenantAttributeId);
        builder.add("attributeKey", attributeKey == null ? "" : attributeKey);
        builder.add("attributeValue", attributeValue == null ? "" : attributeValue);
        builder.add("tenantId", tenantId == null ? 0 : tenantId);

        if (tenant != null)
        {
            tenant.addJson(builder);
        }
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(TenantAttribute.PROP_TENANT_ATTRIBUTE_ID) || column.equals(TenantAttribute.PROP_ATTRIBUTE_KEY) || column.equals(TenantAttribute.PROP_ATTRIBUTE_VALUE) || column.equals(TenantAttribute.PROP_TENANT_ID))
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
        if (column.equals(TenantAttribute.PROP_TENANT_ATTRIBUTE_ID) || column.equals(TenantAttribute.PROP_TENANT_ID))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static TenantAttribute process(ResultSet rs) throws SQLException
    {
        if (rs.getRow() == 0)
        {
            rs.first();
        }
        return new TenantAttribute(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
    }

    public TenantAttribute(Integer TenantAttributeId, String AttributeKey, String AttributeValue, Integer TenantId)
    {
        this.tenantAttributeId = TenantAttributeId;
        this.attributeKey = AttributeKey;
        this.attributeValue = AttributeValue;
        this.tenantId = TenantId;

    }

    public Integer getTenantAttributeId()
    {
        return this.tenantAttributeId;
    }

    public void setTenantAttributeId(Integer TenantAttributeId)
    {
        this.tenantAttributeId = TenantAttributeId;
    }

    public String getAttributeKey()
    {
        return this.attributeKey;
    }

    public void setAttributeKey(String AttributeKey)
    {
        this.attributeKey = AttributeKey;
    }

    public String getAttributeValue()
    {
        return this.attributeValue;
    }

    public void setAttributeValue(String AttributeValue)
    {
        this.attributeValue = AttributeValue;
    }

    public Integer getTenantId()
    {
        return this.tenantId;
    }

    public void setTenantId(Integer TenantId)
    {
        this.tenantId = TenantId;
    }

    public Tenant getTenant()
    {
        return this.tenant;
    }

    public void setTenant(Tenant tenant)
    {
        this.tenant = tenant;
    }
}
