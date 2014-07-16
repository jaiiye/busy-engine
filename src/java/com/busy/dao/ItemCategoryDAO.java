


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ItemCategoryDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemCategory.PROP_ITEM_CATEGORY_ID) || column.equals(ItemCategory.PROP_CATEGORY_ID) || column.equals(ItemCategory.PROP_ITEM_ID) )
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
            if (column.equals(ItemCategory.PROP_ITEM_CATEGORY_ID) || column.equals(ItemCategory.PROP_CATEGORY_ID) || column.equals(ItemCategory.PROP_ITEM_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemCategory processItemCategory(ResultSet rs) throws SQLException
        {        
            return new ItemCategory(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
        
        public static int addItemCategory(Integer CategoryId, Integer ItemId)
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
        
        public static int getAllItemCategoryCount()
        {
            return getAllRecordsCountByTableName("item_category");        
        }
        
        public static ArrayList<ItemCategory> getAllItemCategory()
        {
            ArrayList<ItemCategory> item_category = new ArrayList<ItemCategory>();
            try
            {
                getAllRecordsByTableName("item_category");
                while(rs.next())
                {
                    item_category.add(processItemCategory(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_category;
        }
                
        public static ArrayList<ItemCategory> getItemCategoryPaged(int limit, int offset)
        {
            ArrayList<ItemCategory> item_category = new ArrayList<ItemCategory>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("item_category", limit, offset);
                while (rs.next())
                {
                    item_category.add(processItemCategory(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemCategoryPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_category;
        } 
        
        public static ArrayList<ItemCategory> getAllItemCategoryByColumn(String columnName, String columnValue)
        {
            ArrayList<ItemCategory> item_category = new ArrayList<ItemCategory>();
            try
            {
                getAllRecordsByColumn("item_category", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    item_category.add(processItemCategory(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllItemCategoryByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_category;
        }
        
        public static ItemCategory getItemCategoryByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ItemCategory item_category = new ItemCategory();
            try
            {
                getRecordsByColumnWithLimitAndOffset("item_category", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   item_category = processItemCategory(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getItemCategoryByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return item_category;
        }                
                
        public static void updateItemCategory(Integer ItemCategoryId,Integer CategoryId,Integer ItemId)
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
        
        public static void deleteAllItemCategory()
        {
            try
            {
                updateQuery("DELETE FROM item_category;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllItemCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteItemCategoryById(String id)
        {
            try
            {
                updateQuery("DELETE FROM item_category WHERE ItemCategoryId=" + id + ";");            
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

        public static void deleteItemCategoryByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM item_category WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteItemCategoryByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

