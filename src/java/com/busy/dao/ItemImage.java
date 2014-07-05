package com.busy.dao;

import com.transitionsoft.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ItemImage extends BasicConnection implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_ITEMIMAGEID = "ItemImageId";
    public static final String PROP_ITEMID = "ItemId";
    public static final String PROP_ITEMIMAGENAME = "ItemImageName";
    public static final String PROP_ITEMTHUMBNAILIMAGE = "ItemThumbnailImage";
    public static final String PROP_ITEMALTTAG = "ItemAltTag";

    private Integer itemImageId;
    private Integer itemId;
    private String itemImageName;
    private String itemThumbnailImage;
    private String itemAltTag;

    public ItemImage()
    {
        this.itemImageId = 0;
        this.itemId = 0;
        this.itemImageName = "";
        this.itemThumbnailImage = "";
        this.itemAltTag = "";
    }

    public ItemImage(Integer ItemImageId, Integer ItemId, String ItemImageName, String ItemThumbnailImage, String ItemAltTag)
    {
        this.itemImageId = ItemImageId;
        this.itemId = ItemId;
        this.itemImageName = ItemImageName;
        this.itemThumbnailImage = ItemThumbnailImage;
        this.itemAltTag = ItemAltTag;
    }

    public Integer getItemImageId()
    {
        return this.itemImageId;
    }

    public void setItemImageId(Integer ItemImageId)
    {
        this.itemImageId = ItemImageId;
    }

    public Integer getItemId()
    {
        return this.itemId;
    }

    public void setItemId(Integer ItemId)
    {
        this.itemId = ItemId;
    }

    public String getItemImageName()
    {
        return this.itemImageName;
    }

    public void setItemImageName(String ItemImageName)
    {
        this.itemImageName = ItemImageName;
    }

    public String getItemThumbnailImage()
    {
        return this.itemThumbnailImage;
    }

    public void setItemThumbnailImage(String ItemThumbnailImage)
    {
        this.itemThumbnailImage = ItemThumbnailImage;
    }

    public String getItemAltTag()
    {
        return this.itemAltTag;
    }

    public void setItemAltTag(String ItemAltTag)
    {
        this.itemAltTag = ItemAltTag;
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(ItemImage.PROP_ITEMIMAGEID) || column.equals(ItemImage.PROP_ITEMID) || column.equals(ItemImage.PROP_ITEMIMAGENAME) || column.equals(ItemImage.PROP_ITEMTHUMBNAILIMAGE) || column.equals(ItemImage.PROP_ITEMALTTAG))
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
        if (column.equals(ItemImage.PROP_ITEMIMAGEID) || column.equals(ItemImage.PROP_ITEMID))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static ItemImage processItemImage(ResultSet rs) throws SQLException
    {
        return new ItemImage(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
    }

    public static int addItemImage(Integer ItemId, String ItemImageName, String ItemThumbnailImage, String ItemAltTag)
    {
        int id = 0;
        try
        {

            checkColumnSize(ItemImageName, 255);
            checkColumnSize(ItemThumbnailImage, 255);
            checkColumnSize(ItemAltTag, 255);

            openConnection();
            prepareStatement("INSERT INTO item_image(ItemId,ItemImageName,ItemThumbnailImage,ItemAltTag) VALUES (?,?,?,?);");
            preparedStatement.setInt(1, ItemId);
            preparedStatement.setString(2, ItemImageName);
            preparedStatement.setString(3, ItemThumbnailImage);
            preparedStatement.setString(4, ItemAltTag);

            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_image;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("addItemImage error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return id;
    }

    public static int getAllItemImageCount()
    {
        return getAllRecordsCountByTableName("item_image");
    }

    public static ArrayList<ItemImage> getAllItemImage()
    {
        ArrayList<ItemImage> item_image = new ArrayList<ItemImage>();
        try
        {
            getAllRecordsByTableName("item_image");
            while (rs.next())
            {
                item_image.add(processItemImage(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllItemImage error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return item_image;
    }

    public static ArrayList<ItemImage> getItemImagePaged(int limit, int offset)
    {
        ArrayList<ItemImage> item_image = new ArrayList<ItemImage>();
        try
        {
            getRecordsByTableNameWithLimitAndOffset("item_image", limit, offset);
            while (rs.next())
            {
                item_image.add(processItemImage(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getItemImagePaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return item_image;
    }

    public static ArrayList<ItemImage> getAllItemImageByColumn(String columnName, String columnValue)
    {
        ArrayList<ItemImage> item_image = new ArrayList<ItemImage>();
        try
        {
            getAllRecordsByColumn("item_image", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
            while (rs.next())
            {
                item_image.add(processItemImage(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllItemImageByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return item_image;
    }

    public static ItemImage getItemImageByColumnPaged(String columnName, String columnValue, int limit, int offset)
    {
        ItemImage item_image = new ItemImage();
        try
        {
            getRecordsByColumnWithLimitAndOffset("item_image", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                item_image = processItemImage(rs);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getItemImageByColumnPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return item_image;
    }

    public static void updateItemImage(Integer ItemImageId, Integer ItemId, String ItemImageName, String ItemThumbnailImage, String ItemAltTag)
    {
        try
        {

            checkColumnSize(ItemImageName, 255);
            checkColumnSize(ItemThumbnailImage, 255);
            checkColumnSize(ItemAltTag, 255);

            openConnection();
            prepareStatement("UPDATE item_image SET ItemId=?,ItemImageName=?,ItemThumbnailImage=?,ItemAltTag=? WHERE ItemImageId=?;");
            preparedStatement.setInt(1, ItemId);
            preparedStatement.setString(2, ItemImageName);
            preparedStatement.setString(3, ItemThumbnailImage);
            preparedStatement.setString(4, ItemAltTag);
            preparedStatement.setInt(5, ItemImageId);
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateItemImage error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteAllItemImage()
    {
        try
        {
            updateQuery("DELETE FROM item_image;");
        }
        catch (Exception ex)
        {
            System.out.println("deleteAllItemImage error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteItemImageById(String id)
    {
        try
        {
            updateQuery("DELETE FROM item_image WHERE ItemImageId=" + id + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteItemImageById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteItemImageByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM item_image WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteItemImageByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

}
