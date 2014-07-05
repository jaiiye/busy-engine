package com.busy.dao;

import com.transitionsoft.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SiteResourceType extends BasicConnection implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_SITERESOURCETYPEID = "SiteResourceTypeId";
    public static final String PROP_RESOURCENAME = "ResourceName";
    public static final String PROP_RESOURCEVALUE = "ResourceValue";

    private Integer siteResourceTypeId;
    private String resourceName;
    private String resourceValue;

    public SiteResourceType()
    {
        this.siteResourceTypeId = 0;
        this.resourceName = "";
        this.resourceValue = "";
    }

    public SiteResourceType(Integer SiteResourceTypeId, String ResourceName, String ResourceValue)
    {
        this.siteResourceTypeId = SiteResourceTypeId;
        this.resourceName = ResourceName;
        this.resourceValue = ResourceValue;
    }

    public Integer getSiteResourceTypeId()
    {
        return this.siteResourceTypeId;
    }

    public void setSiteResourceTypeId(Integer SiteResourceTypeId)
    {
        this.siteResourceTypeId = SiteResourceTypeId;
    }

    public String getResourceName()
    {
        return this.resourceName;
    }

    public void setResourceName(String ResourceName)
    {
        this.resourceName = ResourceName;
    }

    public String getResourceValue()
    {
        return this.resourceValue;
    }

    public void setResourceValue(String ResourceValue)
    {
        this.resourceValue = ResourceValue;
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(SiteResourceType.PROP_SITERESOURCETYPEID) || column.equals(SiteResourceType.PROP_RESOURCENAME) || column.equals(SiteResourceType.PROP_RESOURCEVALUE))
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
        if (column.equals(SiteResourceType.PROP_SITERESOURCETYPEID))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static SiteResourceType processSiteResourceType(ResultSet rs) throws SQLException
    {
        return new SiteResourceType(rs.getInt(1), rs.getString(2), rs.getString(3));
    }

    public static int addSiteResourceType(String ResourceName, String ResourceValue)
    {
        int id = 0;
        try
        {
            checkColumnSize(ResourceName, 45);
            checkColumnSize(ResourceValue, 45);

            openConnection();
            prepareStatement("INSERT INTO site_resource_type(ResourceName,ResourceValue) VALUES (?,?);");
            preparedStatement.setString(1, ResourceName);
            preparedStatement.setString(2, ResourceValue);
            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_resource_type;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("addSiteResourceType error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return id;
    }

    public static int getAllSiteResourceTypeCount()
    {
        return getAllRecordsCountByTableName("site_resource_type");
    }

    public static ArrayList<SiteResourceType> getAllSiteResourceType()
    {
        ArrayList<SiteResourceType> site_resource_type = new ArrayList<SiteResourceType>();
        try
        {
            getAllRecordsByTableName("site_resource_type");
            while (rs.next())
            {
                site_resource_type.add(processSiteResourceType(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllSiteResourceType error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return site_resource_type;
    }

    public static ArrayList<SiteResourceType> getSiteResourceTypePaged(int limit, int offset)
    {
        ArrayList<SiteResourceType> site_resource_type = new ArrayList<SiteResourceType>();
        try
        {
            getRecordsByTableNameWithLimitAndOffset("site_resource_type", limit, offset);
            while (rs.next())
            {
                site_resource_type.add(processSiteResourceType(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getSiteResourceTypePaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return site_resource_type;
    }

    public static ArrayList<SiteResourceType> getAllSiteResourceTypeByColumn(String columnName, String columnValue)
    {
        ArrayList<SiteResourceType> site_resource_type = new ArrayList<SiteResourceType>();
        try
        {
            getAllRecordsByColumn("site_resource_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
            while (rs.next())
            {
                site_resource_type.add(processSiteResourceType(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllSiteResourceTypeByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return site_resource_type;
    }

    public static SiteResourceType getSiteResourceTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
    {
        SiteResourceType site_resource_type = new SiteResourceType();
        try
        {
            getRecordsByColumnWithLimitAndOffset("site_resource_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                site_resource_type = processSiteResourceType(rs);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getSiteResourceTypeByColumnPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return site_resource_type;
    }

    public static void updateSiteResourceType(Integer SiteResourceTypeId, String ResourceName, String ResourceValue)
    {
        try
        {

            checkColumnSize(ResourceName, 45);
            checkColumnSize(ResourceValue, 45);

            openConnection();
            prepareStatement("UPDATE site_resource_type SET ResourceName=?,ResourceValue=? WHERE SiteResourceTypeId=?;");
            preparedStatement.setString(1, ResourceName);
            preparedStatement.setString(2, ResourceValue);
            preparedStatement.setInt(3, SiteResourceTypeId);
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateSiteResourceType error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteAllSiteResourceType()
    {
        try
        {
            updateQuery("DELETE FROM site_resource_type;");
        }
        catch (Exception ex)
        {
            System.out.println("deleteAllSiteResourceType error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteSiteResourceTypeById(String id)
    {
        try
        {
            updateQuery("DELETE FROM site_resource_type WHERE SiteResourceTypeId=" + id + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteSiteResourceTypeById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteSiteResourceTypeByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM site_resource_type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteSiteResourceTypeByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

}
