





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemImageDaoImpl extends BasicConnection implements Serializable, ItemImageDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ItemImage find(Integer id)
        {
            return findByColumn("ItemImageId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemImage> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemImage> item_image = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("item_image");
                while(rs.next())
                {
                    item_image.add(ItemImage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemImage object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_image;
         
        }
        
        @Override
        public ArrayList<ItemImage> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemImage> item_imageList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("item_image", limit, offset);
                while (rs.next())
                {
                    item_imageList.add(ItemImage.process(rs));
                }

                
                    for(ItemImage item_image : item_imageList)
                    {
                        
                            getRecordById("Item", item_image.getItemId().toString());
                            item_image.setItem(Item.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemImage method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_imageList;
        }
        
        @Override
        public ArrayList<ItemImage> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemImage> item_image = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("item_image", ItemImage.checkColumnName(columnName), columnValue, ItemImage.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_image.add(ItemImage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemImage's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_image;
        } 
    
        @Override
        public void add(ItemImage obj)
        {
            try
            {
                
                ItemImage.checkColumnSize(obj.getImageName(), 255);
                ItemImage.checkColumnSize(obj.getThumbnailName(), 255);
                ItemImage.checkColumnSize(obj.getAlternateText(), 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_image(ImageName,ThumbnailName,AlternateText,Rank,ItemId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getImageName());
                preparedStatement.setString(2, obj.getThumbnailName());
                preparedStatement.setString(3, obj.getAlternateText());
                preparedStatement.setInt(4, obj.getRank());
                preparedStatement.setInt(5, obj.getItemId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("ItemImage's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String ImageName, String ThumbnailName, String AlternateText, Integer Rank, Integer ItemId)
        {   
            int id = 0;
            try
            {
                
                ItemImage.checkColumnSize(ImageName, 255);
                ItemImage.checkColumnSize(ThumbnailName, 255);
                ItemImage.checkColumnSize(AlternateText, 255);
                
                
                                            
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
        
        
        @Override
        public ItemImage update(ItemImage obj)
        {
           try
            {   
                
                ItemImage.checkColumnSize(obj.getImageName(), 255);
                ItemImage.checkColumnSize(obj.getThumbnailName(), 255);
                ItemImage.checkColumnSize(obj.getAlternateText(), 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_image SET ImageName=?,ThumbnailName=?,AlternateText=?,Rank=?,ItemId=? WHERE ItemImageId=?;");                    
                preparedStatement.setString(1, obj.getImageName());
                preparedStatement.setString(2, obj.getThumbnailName());
                preparedStatement.setString(3, obj.getAlternateText());
                preparedStatement.setInt(4, obj.getRank());
                preparedStatement.setInt(5, obj.getItemId());
                preparedStatement.setInt(6, obj.getItemImageId());
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
            return obj;
        }
        
        public static void update(Integer ItemImageId,String ImageName,String ThumbnailName,String AlternateText,Integer Rank,Integer ItemId)
        {  
            try
            {   
                
                ItemImage.checkColumnSize(ImageName, 255);
                ItemImage.checkColumnSize(ThumbnailName, 255);
                ItemImage.checkColumnSize(AlternateText, 255);
                
                
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("item_image");
        }
        
        
        @Override
        public ItemImage getRelatedInfo(ItemImage item_image)
        {
            
                try
                { 
                    
                        getRecordById("Item", item_image.getItemId().toString());
                        item_image.setItem(Item.process(rs));               
                    

                    }
                catch (SQLException ex)
                {
                    System.out.println("getRelatedInfo error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }                    
              
            
            return item_image;
        }
        
        
        @Override
        public ItemImage getRelatedObjects(ItemImage item_image)
        {
                         
            return item_image;
        }
        
        
        
        @Override
        public void remove(ItemImage obj)
        {            
            try
            {
                updateQuery("DELETE FROM item_image WHERE ItemImageId=" + obj.getItemImageId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM item_image WHERE ItemImageId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM item_image;");
            }
            catch (Exception ex)
            {
                System.out.println("ItemImage's deleteAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        @Override
        public void removeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item_image WHERE " + ItemImage.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("ItemImage's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

