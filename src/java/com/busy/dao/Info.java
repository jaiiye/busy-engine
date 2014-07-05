package com.busy.dao;

import com.transitionsoft.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Info extends BasicConnection implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_INFOID = "InfoId";
    public static final String PROP_INFONAME = "InfoName";
    public static final String PROP_INFODESCRIPTION = "InfoDescription";
    public static final String PROP_FORMID = "FormId";
    public static final String PROP_INFOSEOTITLE = "InfoSeoTitle";
    public static final String PROP_INFOSEODESCRIPTION = "InfoSeoDescription";
    public static final String PROP_INFOSEOKEYWORDS = "InfoSeoKeywords";
    public static final String PROP_SLIDERID = "SliderId";

    private Integer infoId;
    private String infoName;
    private String infoDescription;
    private Integer formId;
    private String infoSeoTitle;
    private String infoSeoDescription;
    private String infoSeoKeywords;
    private Integer sliderId;

    public Info()
    {
        this.infoId = 0;
        this.infoName = "";
        this.infoDescription = "";
        this.formId = 0;
        this.infoSeoTitle = "";
        this.infoSeoDescription = "";
        this.infoSeoKeywords = "";
        this.sliderId = 0;
    }

    public Info(Integer InfoId, String InfoName, String InfoDescription, Integer FormId, String InfoSeoTitle, String InfoSeoDescription, String InfoSeoKeywords, Integer SliderId)
    {
        this.infoId = InfoId;
        this.infoName = InfoName;
        this.infoDescription = InfoDescription;
        this.formId = FormId;
        this.infoSeoTitle = InfoSeoTitle;
        this.infoSeoDescription = InfoSeoDescription;
        this.infoSeoKeywords = InfoSeoKeywords;
        this.sliderId = SliderId;
    }

    public Integer getInfoId()
    {
        return this.infoId;
    }

    public void setInfoId(Integer InfoId)
    {
        this.infoId = InfoId;
    }

    public String getInfoName()
    {
        return this.infoName;
    }

    public void setInfoName(String InfoName)
    {
        this.infoName = InfoName;
    }

    public String getInfoDescription()
    {
        return this.infoDescription;
    }

    public void setInfoDescription(String InfoDescription)
    {
        this.infoDescription = InfoDescription;
    }

    public Integer getFormId()
    {
        return this.formId;
    }

    public void setFormId(Integer FormId)
    {
        this.formId = FormId;
    }

    public String getInfoSeoTitle()
    {
        return this.infoSeoTitle;
    }

    public void setInfoSeoTitle(String InfoSeoTitle)
    {
        this.infoSeoTitle = InfoSeoTitle;
    }

    public String getInfoSeoDescription()
    {
        return this.infoSeoDescription;
    }

    public void setInfoSeoDescription(String InfoSeoDescription)
    {
        this.infoSeoDescription = InfoSeoDescription;
    }

    public String getInfoSeoKeywords()
    {
        return this.infoSeoKeywords;
    }

    public void setInfoSeoKeywords(String InfoSeoKeywords)
    {
        this.infoSeoKeywords = InfoSeoKeywords;
    }

    public Integer getSliderId()
    {
        return this.sliderId;
    }

    public void setSliderId(Integer SliderId)
    {
        this.sliderId = SliderId;
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(Info.PROP_INFOID) || column.equals(Info.PROP_INFONAME) || column.equals(Info.PROP_INFODESCRIPTION) || column.equals(Info.PROP_FORMID) || column.equals(Info.PROP_INFOSEOTITLE) || column.equals(Info.PROP_INFOSEODESCRIPTION) || column.equals(Info.PROP_INFOSEOKEYWORDS) || column.equals(Info.PROP_SLIDERID))
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
        if (column.equals(Info.PROP_INFOID) || column.equals(Info.PROP_FORMID) || column.equals(Info.PROP_SLIDERID))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Info processInfo(ResultSet rs) throws SQLException
    {
        return new Info(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
    }

    public static int addInfo(String InfoName, String InfoDescription, Integer FormId, String InfoSeoTitle, String InfoSeoDescription, String InfoSeoKeywords, Integer SliderId)
    {
        int id = 0;
        try
        {
            checkColumnSize(InfoName, 155);
            checkColumnSize(InfoDescription, 65535);
            checkColumnSize(InfoSeoTitle, 150);
            checkColumnSize(InfoSeoDescription, 155);
            checkColumnSize(InfoSeoKeywords, 155);

            openConnection();
            prepareStatement("INSERT INTO info(InfoName,InfoDescription,FormId,InfoSeoTitle,InfoSeoDescription,InfoSeoKeywords,SliderId) VALUES (?,?,?,?,?,?,?);");
            preparedStatement.setString(1, InfoName);
            preparedStatement.setString(2, InfoDescription);
            preparedStatement.setInt(3, FormId);
            preparedStatement.setString(4, InfoSeoTitle);
            preparedStatement.setString(5, InfoSeoDescription);
            preparedStatement.setString(6, InfoSeoKeywords);
            preparedStatement.setInt(7, SliderId);
            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from info;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("addInfo error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return id;
    }

    public static int getAllInfoCount()
    {
        return getAllRecordsCountByTableName("info");
    }

    public static ArrayList<Info> getAllInfo()
    {
        ArrayList<Info> info = new ArrayList<Info>();
        try
        {
            getAllRecordsByTableName("info");
            while (rs.next())
            {
                info.add(processInfo(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllInfo error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return info;
    }

    public static ArrayList<Info> getInfoPaged(int limit, int offset)
    {
        ArrayList<Info> info = new ArrayList<Info>();
        try
        {
            getRecordsByTableNameWithLimitAndOffset("info", limit, offset);
            while (rs.next())
            {
                info.add(processInfo(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getInfoPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return info;
    }

    public static ArrayList<Info> getAllInfoByColumn(String columnName, String columnValue)
    {
        ArrayList<Info> info = new ArrayList<Info>();
        try
        {
            getAllRecordsByColumn("info", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
            while (rs.next())
            {
                info.add(processInfo(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllInfoByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return info;
    }

    public static Info getInfoByColumnPaged(String columnName, String columnValue, int limit, int offset)
    {
        Info info = new Info();
        try
        {
            getRecordsByColumnWithLimitAndOffset("info", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                info = processInfo(rs);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getInfoByColumnPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return info;
    }

    public static void updateInfo(Integer InfoId, String InfoName, String InfoDescription, Integer FormId, String InfoSeoTitle, String InfoSeoDescription, String InfoSeoKeywords, Integer SliderId)
    {
        try
        {

            checkColumnSize(InfoName, 155);
            checkColumnSize(InfoDescription, 65535);
            checkColumnSize(InfoSeoTitle, 150);
            checkColumnSize(InfoSeoDescription, 155);
            checkColumnSize(InfoSeoKeywords, 155);

            openConnection();
            prepareStatement("UPDATE info SET InfoName=?,InfoDescription=?,FormId=?,InfoSeoTitle=?,InfoSeoDescription=?,InfoSeoKeywords=?,SliderId=? WHERE InfoId=?;");
            preparedStatement.setString(1, InfoName);
            preparedStatement.setString(2, InfoDescription);
            preparedStatement.setInt(3, FormId);
            preparedStatement.setString(4, InfoSeoTitle);
            preparedStatement.setString(5, InfoSeoDescription);
            preparedStatement.setString(6, InfoSeoKeywords);
            preparedStatement.setInt(7, SliderId);
            preparedStatement.setInt(8, InfoId);
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateInfo error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteAllInfo()
    {
        try
        {
            updateQuery("DELETE FROM info;");
        }
        catch (Exception ex)
        {
            System.out.println("deleteAllInfo error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteInfoById(String id)
    {
        try
        {
            updateQuery("DELETE FROM info WHERE InfoId=" + id + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteInfoById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteInfoByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM info WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteInfoByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

}
