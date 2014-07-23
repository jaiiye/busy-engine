





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemAttributeTypeDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemAttributeType.PROP_ITEM_ATTRIBUTE_TYPE_ID) || column.equals(ItemAttributeType.PROP_ATTRIBUTE_NAME) || column.equals(ItemAttributeType.PROP_DESCRIPTION) )
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
            if (column.equals(ItemAttributeType.PROP_ITEM_ATTRIBUTE_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemAttributeType processItemAttributeType(ResultSet rs) throws SQLException
        {        
            return new ItemAttributeType(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addItemAttributeType(String AttributeName, String Description)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(AttributeName, 45);
                checkColumnSize(Description, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO item_attribute_type(AttributeName,Description) VALUES (?,?);");                    
                preparedStatement.setString(1, AttributeName);
                preparedStatement.setString(2, Description);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_attribute_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemAttributeType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllItemAttributeTypeCount()
        {
            return getAllRecordsCountByTableName("item_attribute_type");        
        }
        
        public static ArrayList<ItemAttributeType> getAllItemAttributeType()
        {
            ArrayList<ItemAttributeType> item_attribute_type = new ArrayList<ItemAttributeType>();
            try
            {
                getAllRecordsByTableName("item_attribute_type");
                while(rs.next())
                {
                    item_attribute_type.add(processItemAttributeType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemAttributeType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute_type;
        }
        
        public static ArrayList<ItemAttributeType> getAllItemAttributeTypeWithRelatedInfo()
        {
            ArrayList<ItemAttributeType> item_attribute_typeList = new ArrayList<ItemAttributeType>();
            try
            {
                getAllRecordsByTableName("item_attribute_type");
                while (rs.next())
                {
                    item_attribute_typeList.add(processItemAttributeType(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemAttributeTypeWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute_typeList;
        }
        
        
        public static ItemAttributeType getRelatedInfo(ItemAttributeType item_attribute_type)
        {
           
                  
            
            return item_attribute_type;
        }
        
        public static ItemAttributeType getAllRelatedObjects(ItemAttributeType item_attribute_type)
        {           
            item_attribute_type.setItemAttributeList(ItemAttributeDAO.getAllItemAttributeByColumn("ItemAttributeTypeId", item_attribute_type.getItemAttributeTypeId().toString()));
             
            return item_attribute_type;
        }
        
        
                    
        public static ItemAttributeType getRelatedItemAttributeList(ItemAttributeType item_attribute_type)
        {           
            item_attribute_type.setItemAttributeList(ItemAttributeDAO.getAllItemAttributeByColumn("ItemAttributeTypeId", item_attribute_type.getItemAttributeTypeId().toString()));
            return item_attribute_type;
        }        
        
                
        public static ArrayList<ItemAttributeType> getItemAttributeTypePaged(int limit, int offset)
        {
            ArrayList<ItemAttributeType> item_attribute_type = new ArrayList<ItemAttributeType>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("item_attribute_type", limit, offset);
                while (rs.next())
                {
                    item_attribute_type.add(processItemAttributeType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemAttributeTypePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute_type;
        } 
        
        public static ArrayList<ItemAttributeType> getAllItemAttributeTypeByColumn(String columnName, String columnValue)
        {
            ArrayList<ItemAttributeType> item_attribute_type = new ArrayList<ItemAttributeType>();
            try
            {
                getAllRecordsByColumn("item_attribute_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    item_attribute_type.add(processItemAttributeType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemAttributeTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute_type;
        }
        
        public static ItemAttributeType getItemAttributeTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ItemAttributeType item_attribute_type = new ItemAttributeType();
            try
            {
                getRecordsByColumnWithLimitAndOffset("item_attribute_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_attribute_type = processItemAttributeType(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemAttributeTypeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_attribute_type;
        }                
                
        public static void updateItemAttributeType(Integer ItemAttributeTypeId,String AttributeName,String Description)
        {  
            try
            {   
                
                checkColumnSize(AttributeName, 45);
                checkColumnSize(Description, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_attribute_type SET AttributeName=?,Description=? WHERE ItemAttributeTypeId=?;");                    
                preparedStatement.setString(1, AttributeName);
                preparedStatement.setString(2, Description);
                preparedStatement.setInt(3, ItemAttributeTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemAttributeType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllItemAttributeType()
        {
            try
            {
                updateQuery("DELETE FROM item_attribute_type;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllItemAttributeType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteItemAttributeTypeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM item_attribute_type WHERE ItemAttributeTypeId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemAttributeTypeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteItemAttributeTypeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item_attribute_type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemAttributeTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

