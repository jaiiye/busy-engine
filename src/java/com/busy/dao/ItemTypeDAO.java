











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.ItemType;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemTypeDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemType.PROP_ITEM_TYPE_ID) || column.equals(ItemType.PROP_TYPE_NAME) || column.equals(ItemType.PROP_RANK) )
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
            if (column.equals(ItemType.PROP_ITEM_TYPE_ID) || column.equals(ItemType.PROP_RANK) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemType processItemType(ResultSet rs) throws SQLException
        {        
            return new ItemType(rs.getInt(1), rs.getString(2), rs.getInt(3));
        }
        
        public static int addItemType(String TypeName, Integer Rank)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TypeName, 100);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO item_type(TypeName,Rank) VALUES (?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setInt(2, Rank);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addItemType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllItemTypeCount()
        {
            return getAllRecordsCountByTableName("item_type");        
        }
        
        public static ArrayList<ItemType> getAllItemType()
        {
            ArrayList<ItemType> item_type = new ArrayList<ItemType>();
            try
            {
                getAllRecordsByTableName("item_type");
                while(rs.next())
                {
                    item_type.add(processItemType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_type;
        }
                
        public static ArrayList<ItemType> getItemTypePaged(int limit, int offset)
        {
            ArrayList<ItemType> item_type = new ArrayList<ItemType>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("item_type", limit, offset);
                while (rs.next())
                {
                    item_type.add(processItemType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemTypePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_type;
        } 
        
        public static ArrayList<ItemType> getAllItemTypeByColumn(String columnName, String columnValue)
        {
            ArrayList<ItemType> item_type = new ArrayList<ItemType>();
            try
            {
                getAllRecordsByColumn("item_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    item_type.add(processItemType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_type;
        }
        
        public static ItemType getItemTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ItemType item_type = new ItemType();
            try
            {
                getRecordsByColumnWithLimitAndOffset("item_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_type = processItemType(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemTypeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_type;
        }                
                
        public static void updateItemType(Integer ItemTypeId,String TypeName,Integer Rank)
        {  
            try
            {   
                
                checkColumnSize(TypeName, 100);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_type SET TypeName=?,Rank=? WHERE ItemTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setInt(2, Rank);
                preparedStatement.setInt(3, ItemTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateItemType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllItemType()
        {
            try
            {
                updateQuery("DELETE FROM item_type;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllItemType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteItemTypeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM item_type WHERE ItemTypeId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemTypeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteItemTypeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item_type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

