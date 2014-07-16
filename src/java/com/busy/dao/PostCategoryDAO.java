











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.PostCategory;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class PostCategoryDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(PostCategory.PROP_POST_CATEGORY_ID) || column.equals(PostCategory.PROP_NAME) )
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
            if (column.equals(PostCategory.PROP_POST_CATEGORY_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static PostCategory processPostCategory(ResultSet rs) throws SQLException
        {        
            return new PostCategory(rs.getInt(1), rs.getString(2));
        }
        
        public static int addPostCategory(String Name)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Name, 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO post_category(Name) VALUES (?);");                    
                preparedStatement.setString(1, Name);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from post_category;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addPostCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllPostCategoryCount()
        {
            return getAllRecordsCountByTableName("post_category");        
        }
        
        public static ArrayList<PostCategory> getAllPostCategory()
        {
            ArrayList<PostCategory> post_category = new ArrayList<PostCategory>();
            try
            {
                getAllRecordsByTableName("post_category");
                while(rs.next())
                {
                    post_category.add(processPostCategory(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllPostCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return post_category;
        }
                
        public static ArrayList<PostCategory> getPostCategoryPaged(int limit, int offset)
        {
            ArrayList<PostCategory> post_category = new ArrayList<PostCategory>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("post_category", limit, offset);
                while (rs.next())
                {
                    post_category.add(processPostCategory(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getPostCategoryPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return post_category;
        } 
        
        public static ArrayList<PostCategory> getAllPostCategoryByColumn(String columnName, String columnValue)
        {
            ArrayList<PostCategory> post_category = new ArrayList<PostCategory>();
            try
            {
                getAllRecordsByColumn("post_category", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    post_category.add(processPostCategory(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllPostCategoryByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return post_category;
        }
        
        public static PostCategory getPostCategoryByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            PostCategory post_category = new PostCategory();
            try
            {
                getRecordsByColumnWithLimitAndOffset("post_category", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   post_category = processPostCategory(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getPostCategoryByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return post_category;
        }                
                
        public static void updatePostCategory(Integer PostCategoryId,String Name)
        {  
            try
            {   
                
                checkColumnSize(Name, 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE post_category SET Name=? WHERE PostCategoryId=?;");                    
                preparedStatement.setString(1, Name);
                preparedStatement.setInt(2, PostCategoryId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updatePostCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllPostCategory()
        {
            try
            {
                updateQuery("DELETE FROM post_category;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllPostCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deletePostCategoryById(String id)
        {
            try
            {
                updateQuery("DELETE FROM post_category WHERE PostCategoryId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deletePostCategoryById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deletePostCategoryByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM post_category WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deletePostCategoryByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

