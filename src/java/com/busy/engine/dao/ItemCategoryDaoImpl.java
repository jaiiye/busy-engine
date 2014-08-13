





































    package com.busy.engine.dao;

import com.busy.engine.entity.Category;
import com.busy.engine.entity.ItemCategory;
import com.busy.engine.entity.Item;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemCategoryDaoImpl extends BasicConnection implements Serializable, ItemCategoryDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ItemCategory find(Integer id)
        {
            return findByColumn("ItemCategoryId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemCategory> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemCategory> item_category = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("item_category");
                while(rs.next())
                {
                    item_category.add(ItemCategory.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemCategory object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_category;
         
        }
        
        @Override
        public ArrayList<ItemCategory> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemCategory> item_categoryList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("item_category", limit, offset);
                while (rs.next())
                {
                    item_categoryList.add(ItemCategory.process(rs));
                }

                
                    for(ItemCategory item_category : item_categoryList)
                    {
                        
                            getRecordById("Category", item_category.getCategoryId().toString());
                            item_category.setCategory(Category.process(rs));               
                        
                            getRecordById("Item", item_category.getItemId().toString());
                            item_category.setItem(Item.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemCategory method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_categoryList;
        }
        
        @Override
        public ArrayList<ItemCategory> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemCategory> item_category = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("item_category", ItemCategory.checkColumnName(columnName), columnValue, ItemCategory.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_category.add(ItemCategory.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemCategory's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_category;
        } 
    
        @Override
        public int add(ItemCategory obj)
        {
            int id = 0;
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_category(CategoryId,ItemId) VALUES (?,?);");                    
                preparedStatement.setInt(1, obj.getCategoryId());
                preparedStatement.setInt(2, obj.getItemId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_category;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ItemCategory's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public ItemCategory update(ItemCategory obj)
        {
           try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_category SET CategoryId=?,ItemId=? WHERE ItemCategoryId=?;");                    
                preparedStatement.setInt(1, obj.getCategoryId());
                preparedStatement.setInt(2, obj.getItemId());
                preparedStatement.setInt(3, obj.getItemCategoryId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("ItemCategory's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("item_category");
        }
        
        
        @Override
        public void getRelatedInfo(ItemCategory item_category)
        {
            
                try
                { 
                    
                            getRecordById("Category", item_category.getCategoryId().toString());
                            item_category.setCategory(Category.process(rs));                                       
                    
                            getRecordById("Item", item_category.getItemId().toString());
                            item_category.setItem(Item.process(rs));                                       
                    
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
        public void getRelatedObjects(ItemCategory item_category)
        {
             
        }
        
        @Override
        public boolean remove(ItemCategory obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_category WHERE ItemCategoryId=" + obj.getItemCategoryId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemCategory's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_category WHERE ItemCategoryId=" + id + ";");           
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
                updateQuery("DELETE FROM item_category;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemCategory's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_category WHERE " + ItemCategory.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemCategory's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

