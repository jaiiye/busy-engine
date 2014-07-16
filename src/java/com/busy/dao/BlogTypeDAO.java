


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class BlogTypeDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(BlogType.PROP_BLOG_TYPE_ID) || column.equals(BlogType.PROP_TYPE_NAME) )
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
            if (column.equals(BlogType.PROP_BLOG_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static BlogType processBlogType(ResultSet rs) throws SQLException
        {        
            return new BlogType(rs.getInt(1), rs.getString(2));
        }
        
        public static int addBlogType(String TypeName)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TypeName, 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO blog_type(TypeName) VALUES (?);");                    
                preparedStatement.setString(1, TypeName);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from blog_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addBlogType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllBlogTypeCount()
        {
            return getAllRecordsCountByTableName("blog_type");        
        }
        
        public static ArrayList<BlogType> getAllBlogType()
        {
            ArrayList<BlogType> blog_type = new ArrayList<BlogType>();
            try
            {
                getAllRecordsByTableName("blog_type");
                while(rs.next())
                {
                    blog_type.add(processBlogType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllBlogType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_type;
        }
                
        public static ArrayList<BlogType> getBlogTypePaged(int limit, int offset)
        {
            ArrayList<BlogType> blog_type = new ArrayList<BlogType>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("blog_type", limit, offset);
                while (rs.next())
                {
                    blog_type.add(processBlogType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getBlogTypePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_type;
        } 
        
        public static ArrayList<BlogType> getAllBlogTypeByColumn(String columnName, String columnValue)
        {
            ArrayList<BlogType> blog_type = new ArrayList<BlogType>();
            try
            {
                getAllRecordsByColumn("blog_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    blog_type.add(processBlogType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllBlogTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_type;
        }
        
        public static BlogType getBlogTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            BlogType blog_type = new BlogType();
            try
            {
                getRecordsByColumnWithLimitAndOffset("blog_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   blog_type = processBlogType(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getBlogTypeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_type;
        }                
                
        public static void updateBlogType(Integer BlogTypeId,String TypeName)
        {  
            try
            {   
                
                checkColumnSize(TypeName, 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE blog_type SET TypeName=? WHERE BlogTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setInt(2, BlogTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateBlogType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllBlogType()
        {
            try
            {
                updateQuery("DELETE FROM blog_type;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllBlogType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteBlogTypeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM blog_type WHERE BlogTypeId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteBlogTypeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteBlogTypeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM blog_type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteBlogTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

