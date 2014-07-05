


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemAttribute extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_ITEMATTRIBUTEID = "ItemAttributeId";
        public static final String PROP_KEY = "Key";
        public static final String PROP_VALUE = "Value";
        public static final String PROP_TYPE = "Type";
        
        
        private Integer itemAttributeId;
        private String key;
        private String value;
        private String type;
        
        
        public ItemAttribute()
        {
            this.itemAttributeId = 0; 
       this.key = ""; 
       this.value = ""; 
       this.type = ""; 
        }
        
        public ItemAttribute(Integer ItemAttributeId, String Key, String Value, String Type)
        {
            this.itemAttributeId = ItemAttributeId;
       this.key = Key;
       this.value = Value;
       this.type = Type;
        } 
        
        
            public Integer getItemAttributeId()
            {
                return this.itemAttributeId;
            }
            
            public void setItemAttributeId(Integer ItemAttributeId)
            {
                this.itemAttributeId = ItemAttributeId;
            }
        
            public String getKey()
            {
                return this.key;
            }
            
            public void setKey(String Key)
            {
                this.key = Key;
            }
        
            public String getValue()
            {
                return this.value;
            }
            
            public void setValue(String Value)
            {
                this.value = Value;
            }
        
            public String getType()
            {
                return this.type;
            }
            
            public void setType(String Type)
            {
                this.type = Type;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemAttribute.PROP_ITEMATTRIBUTEID) || column.equals(ItemAttribute.PROP_KEY) || column.equals(ItemAttribute.PROP_VALUE) || column.equals(ItemAttribute.PROP_TYPE) )
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
            if (column.equals(ItemAttribute.PROP_ITEMATTRIBUTEID) )
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
            return new ItemAttribute(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        
        public static int addItemAttribute(String Key, String Value, String Type)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Key, 100);
                checkColumnSize(Value, 255);
                checkColumnSize(Type, 45);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_attribute(Key,Value,Type) VALUES (?,?,?);");                    
                preparedStatement.setString(1, Key);
                preparedStatement.setString(2, Value);
                preparedStatement.setString(3, Type);
                
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
                
        public static void updateItemAttribute(Integer ItemAttributeId,String Key,String Value,String Type)
        {  
            try
            {   
                
                checkColumnSize(Key, 100);
                checkColumnSize(Value, 255);
                checkColumnSize(Type, 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_attribute SET Key=?,Value=?,Type=? WHERE ItemAttributeId=?;");                    
                preparedStatement.setString(1, Key);
                preparedStatement.setString(2, Value);
                preparedStatement.setString(3, Type);
                preparedStatement.setInt(4, ItemAttributeId);
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

