





































    package com.busy.engine.dao;

import com.busy.engine.entity.ItemAttributeType;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemAttributeTypeDaoImpl extends BasicConnection implements Serializable, ItemAttributeTypeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ItemAttributeType find(Integer id)
        {
            return findByColumn("ItemAttributeTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemAttributeType> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemAttributeType> item_attribute_type = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("item_attribute_type");
                while(rs.next())
                {
                    item_attribute_type.add(ItemAttributeType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemAttributeType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute_type;
         
        }
        
        @Override
        public ArrayList<ItemAttributeType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemAttributeType> item_attribute_typeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("item_attribute_type", limit, offset);
                while (rs.next())
                {
                    item_attribute_typeList.add(ItemAttributeType.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object ItemAttributeType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute_typeList;
        }
        
        @Override
        public ArrayList<ItemAttributeType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemAttributeType> item_attribute_type = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("item_attribute_type", ItemAttributeType.checkColumnName(columnName), columnValue, ItemAttributeType.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_attribute_type.add(ItemAttributeType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemAttributeType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute_type;
        } 
    
        @Override
        public int add(ItemAttributeType obj)
        {
            int id = 0;
            try
            {
                
                ItemAttributeType.checkColumnSize(obj.getAttributeName(), 45);
                ItemAttributeType.checkColumnSize(obj.getDescription(), 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_attribute_type(AttributeName,Description) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getAttributeName());
                preparedStatement.setString(2, obj.getDescription());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_attribute_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttributeType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public ItemAttributeType update(ItemAttributeType obj)
        {
           try
            {   
                
                ItemAttributeType.checkColumnSize(obj.getAttributeName(), 45);
                ItemAttributeType.checkColumnSize(obj.getDescription(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_attribute_type SET AttributeName=?,Description=? WHERE ItemAttributeTypeId=?;");                    
                preparedStatement.setString(1, obj.getAttributeName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getItemAttributeTypeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttributeType's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("item_attribute_type");
        }
        
        
        @Override
        public void getRelatedInfo(ItemAttributeType item_attribute_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ItemAttributeType item_attribute_type)
        {
            item_attribute_type.setItemAttributeList(new ItemAttributeDaoImpl().findByColumn("ItemAttributeTypeId", item_attribute_type.getItemAttributeTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ItemAttributeType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_attribute_type WHERE ItemAttributeTypeId=" + obj.getItemAttributeTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttributeType's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_attribute_type WHERE ItemAttributeTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM item_attribute_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttributeType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_attribute_type WHERE " + ItemAttributeType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttributeType's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedItemAttributeList(ItemAttributeType item_attribute_type)
        {           
            item_attribute_type.setItemAttributeList(new ItemAttributeDaoImpl().findByColumn("ItemAttributeTypeId", item_attribute_type.getItemAttributeTypeId().toString(),null,null));
        }        
        
                             
    }

