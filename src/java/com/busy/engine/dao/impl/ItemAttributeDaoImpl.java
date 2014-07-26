





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object ItemAttribute's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute;
        } 
    
        @Override
        public void add(ItemAttribute obj)
        {
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
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttribute's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Key, String Value, String Locale, Integer ItemAttributeTypeId, Integer ItemId)
        {   
            int id = 0;
            try
            {
                
                ItemAttribute.checkColumnSize(Key, 100);
                ItemAttribute.checkColumnSize(Value, 255);
                ItemAttribute.checkColumnSize(Locale, 10);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_attribute(Key,Value,Locale,ItemAttributeTypeId,ItemId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, Key);
                preparedStatement.setString(2, Value);
                preparedStatement.setString(3, Locale);
                preparedStatement.setInt(4, ItemAttributeTypeId);
                preparedStatement.setInt(5, ItemId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_attribute;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemAttribute error: " + ex.getMessage());
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
                System.out.println("updateItemAttribute error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ItemAttributeId,String Key,String Value,String Locale,Integer ItemAttributeTypeId,Integer ItemId)
        {  
            try
            {   
                
                ItemAttribute.checkColumnSize(Key, 100);
                ItemAttribute.checkColumnSize(Value, 255);
                ItemAttribute.checkColumnSize(Locale, 10);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_attribute SET Key=?,Value=?,Locale=?,ItemAttributeTypeId=?,ItemId=? WHERE ItemAttributeId=?;");                    
                preparedStatement.setString(1, Key);
                preparedStatement.setString(2, Value);
                preparedStatement.setString(3, Locale);
                preparedStatement.setInt(4, ItemAttributeTypeId);
                preparedStatement.setInt(5, ItemId);
                preparedStatement.setInt(6, ItemAttributeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemAttribute error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("item_attribute");
        }
        
        
        @Override
        public ItemAttribute getRelatedInfo(ItemAttribute item_attribute)
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
              
            
            return item_attribute;
        }
        
        
        @Override
        public ItemAttribute getRelatedObjects(ItemAttribute item_attribute)
        {
                         
            return item_attribute;
        }
        
        
        
        @Override
        public void remove(ItemAttribute obj)
        {            
            try
            {
                updateQuery("DELETE FROM item_attribute WHERE ItemAttributeId=" + obj.getItemAttributeId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemAttributeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_attribute WHERE ItemAttributeId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemAttributeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_attribute;");
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttribute's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_attribute WHERE " + ItemAttribute.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttribute's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

