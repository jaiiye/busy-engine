











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.ItemAttribute;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemAttributeDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemAttribute.PROP_ITEM_ATTRIBUTE_ID) || column.equals(ItemAttribute.PROP_KEY) || column.equals(ItemAttribute.PROP_VALUE) || column.equals(ItemAttribute.PROP_LOCALE) || column.equals(ItemAttribute.PROP_ATTRIBUTE_TYPE_ID) || column.equals(ItemAttribute.PROP_ITEM_ID) )
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
            if (column.equals(ItemAttribute.PROP_ITEM_ATTRIBUTE_ID) || column.equals(ItemAttribute.PROP_ATTRIBUTE_TYPE_ID) || column.equals(ItemAttribute.PROP_ITEM_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemAttribute processItemAttribute(ResultSet rs) throws SQLException
        {        
            return new ItemAttribute(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
        }
        
        public static int addItemAttribute(String Key, String Value, String Locale, Integer AttributeTypeId, Integer ItemId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Key, 100);
                checkColumnSize(Value, 255);
                checkColumnSize(Locale, 10);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_attribute(Key,Value,Locale,AttributeTypeId,ItemId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, Key);
                preparedStatement.setString(2, Value);
                preparedStatement.setString(3, Locale);
                preparedStatement.setInt(4, AttributeTypeId);
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
        
        public static int getAllItemAttributeCount()
        {
            return getAllRecordsCountByTableName("item_attribute");        
        }
        
        public static ArrayList<ItemAttribute> getAllItemAttribute()
        {
            ArrayList<ItemAttribute> item_attribute = new ArrayList<ItemAttribute>();
            try
            {
                getAllRecordsByTableName("item_attribute");
                while(rs.next())
                {
                    item_attribute.add(processItemAttribute(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemAttribute error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute;
        }
                
        public static ArrayList<ItemAttribute> getItemAttributePaged(int limit, int offset)
        {
            ArrayList<ItemAttribute> item_attribute = new ArrayList<ItemAttribute>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("item_attribute", limit, offset);
                while (rs.next())
                {
                    item_attribute.add(processItemAttribute(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemAttributePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute;
        } 
        
        public static ArrayList<ItemAttribute> getAllItemAttributeByColumn(String columnName, String columnValue)
        {
            ArrayList<ItemAttribute> item_attribute = new ArrayList<ItemAttribute>();
            try
            {
                getAllRecordsByColumn("item_attribute", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    item_attribute.add(processItemAttribute(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemAttributeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute;
        }
        
        public static ItemAttribute getItemAttributeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ItemAttribute item_attribute = new ItemAttribute();
            try
            {
                getRecordsByColumnWithLimitAndOffset("item_attribute", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_attribute = processItemAttribute(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemAttributeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute;
        }                
                
        public static void updateItemAttribute(Integer ItemAttributeId,String Key,String Value,String Locale,Integer AttributeTypeId,Integer ItemId)
        {  
            try
            {   
                
                checkColumnSize(Key, 100);
                checkColumnSize(Value, 255);
                checkColumnSize(Locale, 10);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_attribute SET Key=?,Value=?,Locale=?,AttributeTypeId=?,ItemId=? WHERE ItemAttributeId=?;");                    
                preparedStatement.setString(1, Key);
                preparedStatement.setString(2, Value);
                preparedStatement.setString(3, Locale);
                preparedStatement.setInt(4, AttributeTypeId);
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
        
        public static void deleteAllItemAttribute()
        {
            try
            {
                updateQuery("DELETE FROM item_attribute;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllItemAttribute error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteItemAttributeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM item_attribute WHERE ItemAttributeId=" + id + ";");            
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

        public static void deleteItemAttributeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item_attribute WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemAttributeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

