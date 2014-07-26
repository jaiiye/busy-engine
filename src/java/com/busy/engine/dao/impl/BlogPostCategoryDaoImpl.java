





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class BlogPostCategoryDaoImpl extends BasicConnection implements Serializable, BlogPostCategoryDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public BlogPostCategory find(Integer id)
        {
            return findByColumn("BlogPostCategoryId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<BlogPostCategory> findAll(Integer limit, Integer offset)
        {
            ArrayList<BlogPostCategory> blog_post_category = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("blog_post_category");
                while(rs.next())
                {
                    blog_post_category.add(BlogPostCategory.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("BlogPostCategory object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post_category;
         
        }
        
        @Override
        public ArrayList<BlogPostCategory> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<BlogPostCategory> blog_post_categoryList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("blog_post_category", limit, offset);
                while (rs.next())
                {
                    blog_post_categoryList.add(BlogPostCategory.process(rs));
                }

                
                    for(BlogPostCategory blog_post_category : blog_post_categoryList)
                    {
                        
                            getRecordById("BlogPost", blog_post_category.getBlogPostId().toString());
                            blog_post_category.setBlogPost(BlogPost.process(rs));               
                        
                            getRecordById("PostCategory", blog_post_category.getPostCategoryId().toString());
                            blog_post_category.setPostCategory(PostCategory.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object BlogPostCategory method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post_categoryList;
        }
        
        @Override
        public ArrayList<BlogPostCategory> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<BlogPostCategory> blog_post_category = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("blog_post_category", BlogPostCategory.checkColumnName(columnName), columnValue, BlogPostCategory.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   blog_post_category.add(BlogPostCategory.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object BlogPostCategory's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post_category;
        } 
    
        @Override
        public void add(BlogPostCategory obj)
        {
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO blog_post_category(BlogPostId,PostCategoryId) VALUES (?,?);");                    
                preparedStatement.setInt(1, obj.getBlogPostId());
                preparedStatement.setInt(2, obj.getPostCategoryId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("BlogPostCategory's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(Integer BlogPostId, Integer PostCategoryId)
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
        
        
        @Override
        public BlogPostCategory update(BlogPostCategory obj)
        {
           try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE blog_post_category SET BlogPostId=?,PostCategoryId=? WHERE BlogPostCategoryId=?;");                    
                preparedStatement.setInt(1, obj.getBlogPostId());
                preparedStatement.setInt(2, obj.getPostCategoryId());
                preparedStatement.setInt(3, obj.getBlogPostCategoryId());
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
            return obj;
        }
        
        public static void update(Integer BlogPostCategoryId,Integer BlogPostId,Integer PostCategoryId)
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("blog_post_category");
        }
        
        
        @Override
        public BlogPostCategory getRelatedInfo(BlogPostCategory blog_post_category)
        {
            
                try
                { 
                    
                        getRecordById("BlogPost", blog_post_category.getBlogPostId().toString());
                        blog_post_category.setBlogPost(BlogPost.process(rs));               
                    
                        getRecordById("PostCategory", blog_post_category.getPostCategoryId().toString());
                        blog_post_category.setPostCategory(PostCategory.process(rs));               
                    

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
        
        
        @Override
        public BlogPostCategory getRelatedObjects(BlogPostCategory blog_post_category)
        {
                         
            return blog_post_category;
        }
        
        
        
        @Override
        public void remove(BlogPostCategory obj)
        {            
            try
            {
                updateQuery("DELETE FROM blog_post_category WHERE BlogPostCategoryId=" + obj.getBlogPostCategoryId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM blog_post_category WHERE BlogPostCategoryId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM blog_post_category;");
            }
            catch (Exception ex)
            {
                System.out.println("BlogPostCategory's deleteAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        @Override
        public void removeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM blog_post_category WHERE " + BlogPostCategory.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("BlogPostCategory's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
                             
    }

