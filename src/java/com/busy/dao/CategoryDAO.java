


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class CategoryDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Category.PROP_CATEGORY_ID) || column.equals(Category.PROP_CATEGORY_NAME) || column.equals(Category.PROP_DISCOUNT_ID) )
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
            if (column.equals(Category.PROP_CATEGORY_ID) || column.equals(Category.PROP_DISCOUNT_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Category processCategory(ResultSet rs) throws SQLException
        {        
            return new Category(rs.getInt(1), rs.getString(2), rs.getInt(3));
        }
        
        public static int addCategory(String CategoryName, Integer DiscountId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(CategoryName, 100);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO category(CategoryName,DiscountId) VALUES (?,?);");                    
                preparedStatement.setString(1, CategoryName);
                preparedStatement.setInt(2, DiscountId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from category;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllCategoryCount()
        {
            return getAllRecordsCountByTableName("category");        
        }
        
        public static ArrayList<Category> getAllCategory()
        {
            ArrayList<Category> category = new ArrayList<Category>();
            try
            {
                getAllRecordsByTableName("category");
                while(rs.next())
                {
                    category.add(processCategory(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return category;
        }
                
        public static ArrayList<Category> getCategoryPaged(int limit, int offset)
        {
            ArrayList<Category> category = new ArrayList<Category>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("category", limit, offset);
                while (rs.next())
                {
                    category.add(processCategory(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getCategoryPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return category;
        } 
        
        public static ArrayList<Category> getAllCategoryByColumn(String columnName, String columnValue)
        {
            ArrayList<Category> category = new ArrayList<Category>();
            try
            {
                getAllRecordsByColumn("category", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    category.add(processCategory(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllCategoryByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return category;
        }
        
        public static Category getCategoryByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Category category = new Category();
            try
            {
                getRecordsByColumnWithLimitAndOffset("category", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   category = processCategory(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getCategoryByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return category;
        }                
                
        public static void updateCategory(Integer CategoryId,String CategoryName,Integer DiscountId)
        {  
            try
            {   
                
                checkColumnSize(CategoryName, 100);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE category SET CategoryName=?,DiscountId=? WHERE CategoryId=?;");                    
                preparedStatement.setString(1, CategoryName);
                preparedStatement.setInt(2, DiscountId);
                preparedStatement.setInt(3, CategoryId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllCategory()
        {
            try
            {
                updateQuery("DELETE FROM category;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteCategoryById(String id)
        {
            try
            {
                updateQuery("DELETE FROM category WHERE CategoryId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteCategoryById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteCategoryByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM category WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteCategoryByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

