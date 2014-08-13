





































    package com.busy.engine.dao;

import com.busy.engine.entity.KnowledgeBase;
import com.busy.engine.entity.BlogType;
import com.busy.engine.entity.Blog;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class BlogDaoImpl extends BasicConnection implements Serializable, BlogDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Blog find(Integer id)
        {
            return findByColumn("BlogId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Blog> findAll(Integer limit, Integer offset)
        {
            ArrayList<Blog> blog = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("blog");
                while(rs.next())
                {
                    blog.add(Blog.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Blog object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog;
         
        }
        
        @Override
        public ArrayList<Blog> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Blog> blogList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("blog", limit, offset);
                while (rs.next())
                {
                    blogList.add(Blog.process(rs));
                }

                
                    for(Blog blog : blogList)
                    {
                        
                            getRecordById("BlogType", blog.getBlogTypeId().toString());
                            blog.setBlogType(BlogType.process(rs));               
                        
                            getRecordById("KnowledgeBase", blog.getKnowledgeBaseId().toString());
                            blog.setKnowledgeBase(KnowledgeBase.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object Blog method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blogList;
        }
        
        @Override
        public ArrayList<Blog> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Blog> blog = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("blog", Blog.checkColumnName(columnName), columnValue, Blog.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   blog.add(Blog.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Blog's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog;
        } 
    
        @Override
        public int add(Blog obj)
        {
            int id = 0;
            try
            {
                
                Blog.checkColumnSize(obj.getTopic(), 100);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO blog(Topic,BlogTypeId,KnowledgeBaseId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getTopic());
                preparedStatement.setInt(2, obj.getBlogTypeId());
                preparedStatement.setInt(3, obj.getKnowledgeBaseId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from blog;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Blog's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public Blog update(Blog obj)
        {
           try
            {   
                
                Blog.checkColumnSize(obj.getTopic(), 100);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE blog SET Topic=?,BlogTypeId=?,KnowledgeBaseId=? WHERE BlogId=?;");                    
                preparedStatement.setString(1, obj.getTopic());
                preparedStatement.setInt(2, obj.getBlogTypeId());
                preparedStatement.setInt(3, obj.getKnowledgeBaseId());
                preparedStatement.setInt(4, obj.getBlogId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("Blog's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("blog");
        }
        
        
        @Override
        public void getRelatedInfo(Blog blog)
        {
            
                try
                { 
                    
                            getRecordById("BlogType", blog.getBlogTypeId().toString());
                            blog.setBlogType(BlogType.process(rs));                                       
                    
                            getRecordById("KnowledgeBase", blog.getKnowledgeBaseId().toString());
                            blog.setKnowledgeBase(KnowledgeBase.process(rs));                                       
                    
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
        public void getRelatedObjects(Blog blog)
        {
            blog.setBlogPostList(new BlogPostDaoImpl().findByColumn("BlogId", blog.getBlogId().toString(),null,null));
blog.setUserServiceList(new UserServiceDaoImpl().findByColumn("BlogId", blog.getBlogId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Blog obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM blog WHERE BlogId=" + obj.getBlogId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Blog's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM blog WHERE BlogId=" + id + ";");           
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
                updateQuery("DELETE FROM blog;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Blog's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM blog WHERE " + Blog.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Blog's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedBlogPostList(Blog blog)
        {           
            blog.setBlogPostList(new BlogPostDaoImpl().findByColumn("BlogId", blog.getBlogId().toString(),null,null));
        }        
                    
        public void getRelatedUserServiceList(Blog blog)
        {           
            blog.setUserServiceList(new UserServiceDaoImpl().findByColumn("BlogId", blog.getBlogId().toString(),null,null));
        }        
        
                             
    }

