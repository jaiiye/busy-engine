





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class BlogDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Blog.PROP_BLOG_ID) || column.equals(Blog.PROP_TOPIC) || column.equals(Blog.PROP_BLOG_TYPE_ID) || column.equals(Blog.PROP_KNOWLEDGE_BASE_ID) )
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
            if (column.equals(Blog.PROP_BLOG_ID) || column.equals(Blog.PROP_BLOG_TYPE_ID) || column.equals(Blog.PROP_KNOWLEDGE_BASE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Blog processBlog(ResultSet rs) throws SQLException
        {        
            return new Blog(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        }
        
        public static int addBlog(String Topic, Integer BlogTypeId, Integer KnowledgeBaseId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Topic, 100);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO blog(Topic,BlogTypeId,KnowledgeBaseId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, Topic);
                preparedStatement.setInt(2, BlogTypeId);
                preparedStatement.setInt(3, KnowledgeBaseId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from blog;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addBlog error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllBlogCount()
        {
            return getAllRecordsCountByTableName("blog");        
        }
        
        public static ArrayList<Blog> getAllBlog()
        {
            ArrayList<Blog> blog = new ArrayList<Blog>();
            try
            {
                getAllRecordsByTableName("blog");
                while(rs.next())
                {
                    blog.add(processBlog(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllBlog error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog;
        }
        
        public static ArrayList<Blog> getAllBlogWithRelatedInfo()
        {
            ArrayList<Blog> blogList = new ArrayList<Blog>();
            try
            {
                getAllRecordsByTableName("blog");
                while (rs.next())
                {
                    blogList.add(processBlog(rs));
                }

                
                    for(Blog blog : blogList)
                    {
                        
                            getRecordById("BlogType", blog.getBlogTypeId().toString());
                            blog.setBlogType(BlogTypeDAO.processBlogType(rs));               
                        
                            getRecordById("KnowledgeBase", blog.getKnowledgeBaseId().toString());
                            blog.setKnowledgeBase(KnowledgeBaseDAO.processKnowledgeBase(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllBlogWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blogList;
        }
        
        
        public static Blog getRelatedInfo(Blog blog)
        {
           
                
                    try
                    { 
                        
                            getRecordById("BlogType", blog.getBlogTypeId().toString());
                            blog.setBlogType(BlogTypeDAO.processBlogType(rs));               
                        
                            getRecordById("KnowledgeBase", blog.getKnowledgeBaseId().toString());
                            blog.setKnowledgeBase(KnowledgeBaseDAO.processKnowledgeBase(rs));               
                        

                        }
                    catch (SQLException ex)
                    {
                        System.out.println("getRelatedInfo error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }                    
               
            
            return blog;
        }
        
        public static Blog getAllRelatedObjects(Blog blog)
        {           
            blog.setBlogPostList(BlogPostDAO.getAllBlogPostByColumn("BlogId", blog.getBlogId().toString()));
blog.setUserServiceList(UserServiceDAO.getAllUserServiceByColumn("BlogId", blog.getBlogId().toString()));
             
            return blog;
        }
        
        
                    
        public static Blog getRelatedBlogPostList(Blog blog)
        {           
            blog.setBlogPostList(BlogPostDAO.getAllBlogPostByColumn("BlogId", blog.getBlogId().toString()));
            return blog;
        }        
                    
        public static Blog getRelatedUserServiceList(Blog blog)
        {           
            blog.setUserServiceList(UserServiceDAO.getAllUserServiceByColumn("BlogId", blog.getBlogId().toString()));
            return blog;
        }        
        
                
        public static ArrayList<Blog> getBlogPaged(int limit, int offset)
        {
            ArrayList<Blog> blog = new ArrayList<Blog>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("blog", limit, offset);
                while (rs.next())
                {
                    blog.add(processBlog(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getBlogPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog;
        } 
        
        public static ArrayList<Blog> getAllBlogByColumn(String columnName, String columnValue)
        {
            ArrayList<Blog> blog = new ArrayList<Blog>();
            try
            {
                getAllRecordsByColumn("blog", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    blog.add(processBlog(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllBlogByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog;
        }
        
        public static Blog getBlogByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Blog blog = new Blog();
            try
            {
                getRecordsByColumnWithLimitAndOffset("blog", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   blog = processBlog(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getBlogByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog;
        }                
                
        public static void updateBlog(Integer BlogId,String Topic,Integer BlogTypeId,Integer KnowledgeBaseId)
        {  
            try
            {   
                
                checkColumnSize(Topic, 100);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE blog SET Topic=?,BlogTypeId=?,KnowledgeBaseId=? WHERE BlogId=?;");                    
                preparedStatement.setString(1, Topic);
                preparedStatement.setInt(2, BlogTypeId);
                preparedStatement.setInt(3, KnowledgeBaseId);
                preparedStatement.setInt(4, BlogId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateBlog error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllBlog()
        {
            try
            {
                updateQuery("DELETE FROM blog;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllBlog error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteBlogById(String id)
        {
            try
            {
                updateQuery("DELETE FROM blog WHERE BlogId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteBlogById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteBlogByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM blog WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteBlogByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

