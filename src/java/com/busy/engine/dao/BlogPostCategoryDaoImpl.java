





































    package com.busy.engine.dao;

import com.busy.engine.entity.PostCategory;
import com.busy.engine.entity.BlogPostCategory;
import com.busy.engine.entity.BlogPost;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("BlogPostCategory's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_post_category;
        } 
    
        @Override
        public int add(BlogPostCategory obj)
        {
            int id = 0;
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO blog_post_category(BlogPostId,PostCategoryId) VALUES (?,?);");                    
                preparedStatement.setInt(1, obj.getBlogPostId());
                preparedStatement.setInt(2, obj.getPostCategoryId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from blog_post_category;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("BlogPostCategory's add method error: " + ex.getMessage());
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
                System.out.println("BlogPostCategory's update error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("blog_post_category");
        }
        
        
        @Override
        public void getRelatedInfo(BlogPostCategory blog_post_category)
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
              
        }
        
        @Override
        public void getRelatedObjects(BlogPostCategory blog_post_category)
        {
             
        }
        
        @Override
        public boolean remove(BlogPostCategory obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM blog_post_category WHERE BlogPostCategoryId=" + obj.getBlogPostCategoryId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogPostCategory's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM blog_post_category WHERE BlogPostCategoryId=" + id + ";");           
                success = true;           
            }
            catch (Exception ex)
            {
                System.out.println("removeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeAll()
        {
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM blog_post_category;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogPostCategory's removeAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeByColumn(String columnName, String columnValue)
        {
            boolean success = false;
            try
            { 
                updateQuery("DELETE FROM blog_post_category WHERE " + BlogPostCategory.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("BlogPostCategory's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

