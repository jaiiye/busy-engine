





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object ItemCategory's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_category;
        } 
    
        @Override
        public void add(ItemCategory obj)
        {
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_category(CategoryId,ItemId) VALUES (?,?);");                    
                preparedStatement.setInt(1, obj.getCategoryId());
                preparedStatement.setInt(2, obj.getItemId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("ItemCategory's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Integer CategoryId, Integer ItemId)
        {   
            int id = 0;
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_category(CategoryId,ItemId) VALUES (?,?);");                    
                preparedStatement.setInt(1, CategoryId);
                preparedStatement.setInt(2, ItemId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_category;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemCategory error: " + ex.getMessage());
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
                System.out.println("updateItemCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ItemCategoryId,Integer CategoryId,Integer ItemId)
        {  
            try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_category SET CategoryId=?,ItemId=? WHERE ItemCategoryId=?;");                    
                preparedStatement.setInt(1, CategoryId);
                preparedStatement.setInt(2, ItemId);
                preparedStatement.setInt(3, ItemCategoryId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("item_category");
        }
        
        
        @Override
        public ItemCategory getRelatedInfo(ItemCategory item_category)
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
              
            
            return item_category;
        }
        
        
        @Override
        public ItemCategory getRelatedObjects(ItemCategory item_category)
        {
                         
            return item_category;
        }
        
        
        
        @Override
        public void remove(ItemCategory obj)
        {            
            try
            {
                updateQuery("DELETE FROM item_category WHERE ItemCategoryId=" + obj.getItemCategoryId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemCategoryById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_category WHERE ItemCategoryId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemCategoryById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_category;");
            }
            catch (Exception ex)
            {
                System.out.println("ItemCategory's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_category WHERE " + ItemCategory.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("ItemCategory's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

