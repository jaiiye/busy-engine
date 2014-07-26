





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object Blog's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog;
        } 
    
        @Override
        public void add(Blog obj)
        {
            try
            {
                
                Blog.checkColumnSize(obj.getTopic(), 100);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO blog(Topic,BlogTypeId,KnowledgeBaseId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getTopic());
                preparedStatement.setInt(2, obj.getBlogTypeId());
                preparedStatement.setInt(3, obj.getKnowledgeBaseId());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("Blog's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Topic, Integer BlogTypeId, Integer KnowledgeBaseId)
        {   
            int id = 0;
            try
            {
                
                Blog.checkColumnSize(Topic, 100);
                
                
                                            
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
                System.out.println("updateBlog error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer BlogId,String Topic,Integer BlogTypeId,Integer KnowledgeBaseId)
        {  
            try
            {   
                
                Blog.checkColumnSize(Topic, 100);
                
                
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("blog");
        }
        
        
        @Override
        public Blog getRelatedInfo(Blog blog)
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
              
            
            return blog;
        }
        
        
        @Override
        public Blog getRelatedObjects(Blog blog)
        {
            blog.setBlogPostList(new BlogPostDaoImpl().findByColumn("BlogId", blog.getBlogId().toString(),null,null));
blog.setUserServiceList(new UserServiceDaoImpl().findByColumn("BlogId", blog.getBlogId().toString(),null,null));
             
            return blog;
        }
        
        
        
        @Override
        public void remove(Blog obj)
        {            
            try
            {
                updateQuery("DELETE FROM blog WHERE BlogId=" + obj.getBlogId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM blog WHERE BlogId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM blog;");
            }
            catch (Exception ex)
            {
                System.out.println("Blog's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM blog WHERE " + Blog.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Blog's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Blog getRelatedBlogPostList(Blog blog)
        {           
            blog.setBlogPostList(new BlogPostDaoImpl().findByColumn("BlogId", blog.getBlogId().toString(),null,null));
            return blog;
        }        
                    
        public Blog getRelatedUserServiceList(Blog blog)
        {           
            blog.setUserServiceList(new UserServiceDaoImpl().findByColumn("BlogId", blog.getBlogId().toString(),null,null));
            return blog;
        }        
        
                             
    }

