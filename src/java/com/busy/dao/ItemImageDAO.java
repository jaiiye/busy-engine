


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemImageDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemImage.PROP_ITEM_IMAGE_ID) || column.equals(ItemImage.PROP_IMAGE_NAME) || column.equals(ItemImage.PROP_THUMBNAIL_NAME) || column.equals(ItemImage.PROP_ALTERNATE_TEXT) || column.equals(ItemImage.PROP_RANK) || column.equals(ItemImage.PROP_ITEM_ID) )
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
            if (column.equals(ItemImage.PROP_ITEM_IMAGE_ID) || column.equals(ItemImage.PROP_RANK) || column.equals(ItemImage.PROP_ITEM_ID) )
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
            return new ItemImage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
        }
        
        public static int addItemImage(String ImageName, String ThumbnailName, String AlternateText, Integer Rank, Integer ItemId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(ImageName, 255);
                checkColumnSize(ThumbnailName, 255);
                checkColumnSize(AlternateText, 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_image(ImageName,ThumbnailName,AlternateText,Rank,ItemId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, ImageName);
                preparedStatement.setString(2, ThumbnailName);
                preparedStatement.setString(3, AlternateText);
                preparedStatement.setInt(4, Rank);
                preparedStatement.setInt(5, ItemId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_image;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
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
                while(rs.next())
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
                while(rs.next())
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
                while(rs.next())
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
                
        public static void updateItemImage(Integer ItemImageId,String ImageName,String ThumbnailName,String AlternateText,Integer Rank,Integer ItemId)
        {  
            try
            {   
                
                checkColumnSize(ImageName, 255);
                checkColumnSize(ThumbnailName, 255);
                checkColumnSize(AlternateText, 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_image SET ImageName=?,ThumbnailName=?,AlternateText=?,Rank=?,ItemId=? WHERE ItemImageId=?;");                    
                preparedStatement.setString(1, ImageName);
                preparedStatement.setString(2, ThumbnailName);
                preparedStatement.setString(3, AlternateText);
                preparedStatement.setInt(4, Rank);
                preparedStatement.setInt(5, ItemId);
                preparedStatement.setInt(6, ItemImageId);
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

