





































    package com.busy.engine.dao;

import com.busy.engine.entity.ItemAttributeType;
import com.busy.engine.entity.ItemAttribute;
import com.busy.engine.entity.Item;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemAttributeDaoImpl extends BasicConnection implements Serializable, ItemAttributeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ItemAttribute find(Integer id)
        {
            return findByColumn("ItemAttributeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemAttribute> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemAttribute> item_attribute = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("item_attribute");
                while(rs.next())
                {
                    item_attribute.add(ItemAttribute.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemAttribute object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute;
         
        }
        
        @Override
        public ArrayList<ItemAttribute> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemAttribute> item_attributeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("item_attribute", limit, offset);
                while (rs.next())
                {
                    item_attributeList.add(ItemAttribute.process(rs));
                }

                
                    for(ItemAttribute item_attribute : item_attributeList)
                    {
                        
                            getRecordById("ItemAttributeType", item_attribute.getItemAttributeTypeId().toString());
                            item_attribute.setItemAttributeType(ItemAttributeType.process(rs));               
                        
                            getRecordById("Item", item_attribute.getItemId().toString());
                            item_attribute.setItem(Item.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemAttribute method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attributeList;
        }
        
        @Override
        public ArrayList<ItemAttribute> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemAttribute> item_attribute = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("item_attribute", ItemAttribute.checkColumnName(columnName), columnValue, ItemAttribute.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_attribute.add(ItemAttribute.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemAttribute's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute;
        } 
    
        @Override
        public int add(ItemAttribute obj)
        {
            int id = 0;
            try
            {
                
                ItemAttribute.checkColumnSize(obj.getKey(), 100);
                ItemAttribute.checkColumnSize(obj.getValue(), 255);
                ItemAttribute.checkColumnSize(obj.getLocale(), 10);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_attribute(Key,Value,Locale,ItemAttributeTypeId,ItemId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getKey());
                preparedStatement.setString(2, obj.getValue());
                preparedStatement.setString(3, obj.getLocale());
                preparedStatement.setInt(4, obj.getItemAttributeTypeId());
                preparedStatement.setInt(5, obj.getItemId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_attribute;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttribute's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public ItemAttribute update(ItemAttribute obj)
        {
           try
            {   
                
                ItemAttribute.checkColumnSize(obj.getKey(), 100);
                ItemAttribute.checkColumnSize(obj.getValue(), 255);
                ItemAttribute.checkColumnSize(obj.getLocale(), 10);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_attribute SET Key=?,Value=?,Locale=?,ItemAttributeTypeId=?,ItemId=? WHERE ItemAttributeId=?;");                    
                preparedStatement.setString(1, obj.getKey());
                preparedStatement.setString(2, obj.getValue());
                preparedStatement.setString(3, obj.getLocale());
                preparedStatement.setInt(4, obj.getItemAttributeTypeId());
                preparedStatement.setInt(5, obj.getItemId());
                preparedStatement.setInt(6, obj.getItemAttributeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttribute's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("item_attribute");
        }
        
        
        @Override
        public void getRelatedInfo(ItemAttribute item_attribute)
        {
            
                try
                { 
                    
                            getRecordById("ItemAttributeType", item_attribute.getItemAttributeTypeId().toString());
                            item_attribute.setItemAttributeType(ItemAttributeType.process(rs));                                       
                    
                            getRecordById("Item", item_attribute.getItemId().toString());
                            item_attribute.setItem(Item.process(rs));                                       
                    
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
        public void getRelatedObjects(ItemAttribute item_attribute)
        {
             
        }
        
        @Override
        public boolean remove(ItemAttribute obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_attribute WHERE ItemAttributeId=" + obj.getItemAttributeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttribute's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_attribute WHERE ItemAttributeId=" + id + ";");           
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
                updateQuery("DELETE FROM item_attribute;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttribute's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_attribute WHERE " + ItemAttribute.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttribute's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

