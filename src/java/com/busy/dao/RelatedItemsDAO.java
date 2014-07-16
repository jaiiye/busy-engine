package com.busy.dao;

import com.transitionsoft.BasicConnection;
import com.busy.entity.RelatedItems;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RelatedItemsDAO extends BasicConnection implements Serializable
{

    private static final long serialVersionUID = 1L;

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(RelatedItems.PROP_RELATED_ITEM_ID) || column.equals(RelatedItems.PROP_ITEM1) || column.equals(RelatedItems.PROP_ITEM2))
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
        if (column.equals(RelatedItems.PROP_RELATED_ITEM_ID) || column.equals(RelatedItems.PROP_ITEM1) || column.equals(RelatedItems.PROP_ITEM2))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static RelatedItems processRelatedItems(ResultSet rs) throws SQLException
    {
        return new RelatedItems(rs.getInt(1), rs.getInt(2), rs.getInt(3));
    }

    public static int addRelatedItems(Integer Item1, Integer Item2)
    {
        int id = 0;
        try
        {

            openConnection();
            prepareStatement("INSERT INTO related_items(Item1,Item2) VALUES (?,?);");
            preparedStatement.setInt(1, Item1);
            preparedStatement.setInt(2, Item2);

            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from related_items;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("addRelatedItems error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return id;
    }

    public static int getAllRelatedItemsCount()
    {
        return getAllRecordsCountByTableName("related_items");
    }

    public static ArrayList<RelatedItems> getAllRelatedItems()
    {
        ArrayList<RelatedItems> related_items = new ArrayList<RelatedItems>();
        try
        {
            getAllRecordsByTableName("related_items");
            while (rs.next())
            {
                related_items.add(processRelatedItems(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllRelatedItems error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return related_items;
    }

    public static ArrayList<RelatedItems> getRelatedItemsPaged(int limit, int offset)
    {
        ArrayList<RelatedItems> related_items = new ArrayList<RelatedItems>();
        try
        {
            getRecordsByTableNameWithLimitAndOffset("related_items", limit, offset);
            while (rs.next())
            {
                related_items.add(processRelatedItems(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getRelatedItemsPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return related_items;
    }

    public static ArrayList<RelatedItems> getAllRelatedItemsByColumn(String columnName, String columnValue)
    {
        ArrayList<RelatedItems> related_items = new ArrayList<RelatedItems>();
        try
        {
            getAllRecordsByColumn("related_items", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
            while (rs.next())
            {
                related_items.add(processRelatedItems(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllRelatedItemsByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return related_items;
    }

    public static RelatedItems getRelatedItemsByColumnPaged(String columnName, String columnValue, int limit, int offset)
    {
        RelatedItems related_items = new RelatedItems();
        try
        {
            getRecordsByColumnWithLimitAndOffset("related_items", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                related_items = processRelatedItems(rs);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getRelatedItemsByColumnPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return related_items;
    }

    public static void updateRelatedItems(Integer RelatedItemId, Integer Item1, Integer Item2)
    {
        try
        {

            openConnection();
            prepareStatement("UPDATE related_items SET Item1=?,Item2=? WHERE RelatedItemId=?;");
            preparedStatement.setInt(1, Item1);
            preparedStatement.setInt(2, Item2);
            preparedStatement.setInt(3, RelatedItemId);
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateRelatedItems error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteAllRelatedItems()
    {
        try
        {
            updateQuery("DELETE FROM related_items;");
        }
        catch (Exception ex)
        {
            System.out.println("deleteAllRelatedItems error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteRelatedItemsById(String id)
    {
        try
        {
            updateQuery("DELETE FROM related_items WHERE RelatedItemsId=" + id + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteRelatedItemsById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteRelatedItemsByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM related_items WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteRelatedItemsByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

}
