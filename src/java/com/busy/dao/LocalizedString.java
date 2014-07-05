package com.busy.dao;

import com.transitionsoft.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LocalizedString extends BasicConnection implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_STRINGID = "StringId";
    public static final String PROP_LOCALEID = "LocaleId";
    public static final String PROP_STRINGVALUE = "StringValue";

    private Integer stringId;
    private Integer localeId;
    private String stringValue;

    public LocalizedString()
    {
        this.stringId = 0;
        this.localeId = 0;
        this.stringValue = "";
    }

    public LocalizedString(Integer StringId, Integer LocaleId, String StringValue)
    {
        this.stringId = StringId;
        this.localeId = LocaleId;
        this.stringValue = StringValue;
    }

    public Integer getStringId()
    {
        return this.stringId;
    }

    public void setStringId(Integer StringId)
    {
        this.stringId = StringId;
    }

    public Integer getLocaleId()
    {
        return this.localeId;
    }

    public void setLocaleId(Integer LocaleId)
    {
        this.localeId = LocaleId;
    }

    public String getStringValue()
    {
        return this.stringValue;
    }

    public void setStringValue(String StringValue)
    {
        this.stringValue = StringValue;
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(LocalizedString.PROP_STRINGID) || column.equals(LocalizedString.PROP_LOCALEID) || column.equals(LocalizedString.PROP_STRINGVALUE))
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
        if (column.equals(LocalizedString.PROP_STRINGID) || column.equals(LocalizedString.PROP_LOCALEID))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static LocalizedString processLocalizedString(ResultSet rs) throws SQLException
    {
        return new LocalizedString(rs.getInt(1), rs.getInt(2), rs.getString(3));
    }

    public static int addLocalizedString(Integer LocaleId, String StringValue)
    {
        int id = 0;
        try
        {

            checkColumnSize(StringValue, 255);

            openConnection();
            prepareStatement("INSERT INTO localized_string(LocaleId,StringValue) VALUES (?,?);");
            preparedStatement.setInt(1, LocaleId);
            preparedStatement.setString(2, StringValue);
            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from localized_string;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("addLocalizedString error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return id;
    }

    public static int getAllLocalizedStringCount()
    {
        return getAllRecordsCountByTableName("localized_string");
    }

    public static ArrayList<LocalizedString> getAllLocalizedString()
    {
        ArrayList<LocalizedString> localized_string = new ArrayList<LocalizedString>();
        try
        {
            getAllRecordsByTableName("localized_string");
            while (rs.next())
            {
                localized_string.add(processLocalizedString(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllLocalizedString error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return localized_string;
    }

    public static ArrayList<LocalizedString> getLocalizedStringPaged(int limit, int offset)
    {
        ArrayList<LocalizedString> localized_string = new ArrayList<LocalizedString>();
        try
        {
            getRecordsByTableNameWithLimitAndOffset("localized_string", limit, offset);
            while (rs.next())
            {
                localized_string.add(processLocalizedString(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getLocalizedStringPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return localized_string;
    }

    public static ArrayList<LocalizedString> getAllLocalizedStringByColumn(String columnName, String columnValue)
    {
        ArrayList<LocalizedString> localized_string = new ArrayList<LocalizedString>();
        try
        {
            getAllRecordsByColumn("localized_string", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
            while (rs.next())
            {
                localized_string.add(processLocalizedString(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllLocalizedStringByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return localized_string;
    }

    public static LocalizedString getLocalizedStringByColumnPaged(String columnName, String columnValue, int limit, int offset)
    {
        LocalizedString localized_string = new LocalizedString();
        try
        {
            getRecordsByColumnWithLimitAndOffset("localized_string", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                localized_string = processLocalizedString(rs);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getLocalizedStringByColumnPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return localized_string;
    }

    public static void updateLocalizedString(Integer StringId, Integer LocaleId, String StringValue)
    {
        try
        {

            checkColumnSize(StringValue, 255);

            openConnection();
            prepareStatement("UPDATE localized_string SET LocaleId=?,StringValue=? WHERE StringId=?;");
            preparedStatement.setInt(1, LocaleId);
            preparedStatement.setString(2, StringValue);
            preparedStatement.setInt(3, StringId);
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateLocalizedString error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteAllLocalizedString()
    {
        try
        {
            updateQuery("DELETE FROM localized_string;");
        }
        catch (Exception ex)
        {
            System.out.println("deleteAllLocalizedString error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteLocalizedStringById(String id)
    {
        try
        {
            updateQuery("DELETE FROM localized_string WHERE LocalizedStringId=" + id + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteLocalizedStringById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteLocalizedStringByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM localized_string WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteLocalizedStringByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

}
