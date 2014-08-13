





































    package com.busy.engine.dao;

import com.busy.engine.entity.ItemImage;
import com.busy.engine.entity.Item;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("ItemImage's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_image;
        } 
    
        @Override
        public int add(ItemImage obj)
        {
            int id = 0;
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
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_image;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ItemImage's add method error: " + ex.getMessage());
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
                System.out.println("ItemImage's update error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("item_image");
        }
        
        
        @Override
        public void getRelatedInfo(ItemImage item_image)
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
              
        }
        
        @Override
        public void getRelatedObjects(ItemImage item_image)
        {
             
        }
        
        @Override
        public boolean remove(ItemImage obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_image WHERE ItemImageId=" + obj.getItemImageId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemImage's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM item_image WHERE ItemImageId=" + id + ";");           
                success = true;           
            }
            catch (Exception ex)
            {
                System.out.println("removeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeAll()
        {
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_image;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemImage's removeAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeByColumn(String columnName, String columnValue)
        {
            boolean success = false;
            try
            { 
                updateQuery("DELETE FROM item_image WHERE " + ItemImage.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemImage's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

