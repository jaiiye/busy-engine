





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class BlogPostCategoryDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(BlogPostCategory.PROP_BLOG_POST_CATEGORY_ID) || column.equals(BlogPostCategory.PROP_BLOG_POST_ID) || column.equals(BlogPostCategory.PROP_POST_CATEGORY_ID) )
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
            if (column.equals(BlogPostCategory.PROP_BLOG_POST_CATEGORY_ID) || column.equals(BlogPostCategory.PROP_BLOG_POST_ID) || column.equals(BlogPostCategory.PROP_POST_CATEGORY_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static BlogPostCategory processBlogPostCategory(ResultSet rs) throws SQLException
        {        
            return new BlogPostCategory(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
        
        public static int addBlogPostCategory(Integer BlogPostId, Integer PostCategoryId)
        {   
            int id = 0;
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO blog_post_category(BlogPostId,PostCategoryId) VALUES (?,?);");                    
                preparedStatement.setInt(1, BlogPostId);
                preparedStatement.setInt(2, PostCategoryId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from blog_post_category;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addBlogPostCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllBlogPostCategoryCount()
        {
            return getAllRecordsCountByTableName("blog_post_category");        
        }
        
        public static ArrayList<BlogPostCategory> getAllBlogPostCategory()
        {
            ArrayList<BlogPostCategory> blog_post_category = new ArrayList<BlogPostCategory>();
            try
            {
                getAllRecordsByTableName("blog_post_category");
                while(rs.next())
                {
                    blog_post_category.add(processBlogPostCategory(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllBlogPostCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post_category;
        }
        
        public static ArrayList<BlogPostCategory> getAllBlogPostCategoryWithRelatedInfo()
        {
            ArrayList<BlogPostCategory> blog_post_categoryList = new ArrayList<BlogPostCategory>();
            try
            {
                getAllRecordsByTableName("blog_post_category");
                while (rs.next())
                {
                    blog_post_categoryList.add(processBlogPostCategory(rs));
                }

                
                    for(BlogPostCategory blog_post_category : blog_post_categoryList)
                    {
                        
                            getRecordById("BlogPost", blog_post_category.getBlogPostId().toString());
                            blog_post_category.setBlogPost(BlogPostDAO.processBlogPost(rs));               
                        
                            getRecordById("PostCategory", blog_post_category.getPostCategoryId().toString());
                            blog_post_category.setPostCategory(PostCategoryDAO.processPostCategory(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllBlogPostCategoryWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post_categoryList;
        }
        
        
        public static BlogPostCategory getRelatedInfo(BlogPostCategory blog_post_category)
        {
           
                
                    try
                    { 
                        
                            getRecordById("BlogPost", blog_post_category.getBlogPostId().toString());
                            blog_post_category.setBlogPost(BlogPostDAO.processBlogPost(rs));               
                        
                            getRecordById("PostCategory", blog_post_category.getPostCategoryId().toString());
                            blog_post_category.setPostCategory(PostCategoryDAO.processPostCategory(rs));               
                        

                        }
                    catch (SQLException ex)
                    {
                        System.out.println("getRelatedInfo error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }                    
               
            
            return blog_post_category;
        }
        
        public static BlogPostCategory getAllRelatedObjects(BlogPostCategory blog_post_category)
        {           
                         
            return blog_post_category;
        }
        
        
        
                
        public static ArrayList<BlogPostCategory> getBlogPostCategoryPaged(int limit, int offset)
        {
            ArrayList<BlogPostCategory> blog_post_category = new ArrayList<BlogPostCategory>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("blog_post_category", limit, offset);
                while (rs.next())
                {
                    blog_post_category.add(processBlogPostCategory(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getBlogPostCategoryPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post_category;
        } 
        
        public static ArrayList<BlogPostCategory> getAllBlogPostCategoryByColumn(String columnName, String columnValue)
        {
            ArrayList<BlogPostCategory> blog_post_category = new ArrayList<BlogPostCategory>();
            try
            {
                getAllRecordsByColumn("blog_post_category", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    blog_post_category.add(processBlogPostCategory(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllBlogPostCategoryByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post_category;
        }
        
        public static BlogPostCategory getBlogPostCategoryByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            BlogPostCategory blog_post_category = new BlogPostCategory();
            try
            {
                getRecordsByColumnWithLimitAndOffset("blog_post_category", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   blog_post_category = processBlogPostCategory(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getBlogPostCategoryByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post_category;
        }                
                
        public static void updateBlogPostCategory(Integer BlogPostCategoryId,Integer BlogPostId,Integer PostCategoryId)
        {  
            try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE blog_post_category SET BlogPostId=?,PostCategoryId=? WHERE BlogPostCategoryId=?;");                    
                preparedStatement.setInt(1, BlogPostId);
                preparedStatement.setInt(2, PostCategoryId);
                preparedStatement.setInt(3, BlogPostCategoryId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateBlogPostCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllBlogPostCategory()
        {
            try
            {
                updateQuery("DELETE FROM blog_post_category;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllBlogPostCategory error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteBlogPostCategoryById(String id)
        {
            try
            {
                updateQuery("DELETE FROM blog_post_category WHERE BlogPostCategoryId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteBlogPostCategoryById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteBlogPostCategoryByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM blog_post_category WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteBlogPostCategoryByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

