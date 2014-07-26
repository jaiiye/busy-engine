





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class PostCategoryDaoImpl extends BasicConnection implements Serializable, PostCategoryDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public PostCategory find(Integer id)
        {
            return findByColumn("PostCategoryId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<PostCategory> findAll(Integer limit, Integer offset)
        {
            ArrayList<PostCategory> post_category = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("post_category");
                while(rs.next())
                {
                    post_category.add(PostCategory.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("PostCategory object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return post_category;
         
        }
        
        @Override
        public ArrayList<PostCategory> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<PostCategory> post_categoryList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("post_category", limit, offset);
                while (rs.next())
                {
                    post_categoryList.add(PostCategory.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object PostCategory method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return post_categoryList;
        }
        
        @Override
        public ArrayList<PostCategory> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<PostCategory> post_category = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("post_category", PostCategory.checkColumnName(columnName), columnValue, PostCategory.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   post_category.add(PostCategory.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object PostCategory's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return post_category;
        } 
    
        @Override
        public void add(PostCategory obj)
        {
            try
            {
                
                PostCategory.checkColumnSize(obj.getName(), 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO post_category(Name) VALUES (?);");                    
                preparedStatement.setString(1, obj.getName());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("PostCategory's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Name)
        {   
            int id = 0;
            try
            {
                
                PostCategory.checkColumnSize(Name, 100);
                                            
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
        
        
        @Override
        public PostCategory update(PostCategory obj)
        {
           try
            {   
                
                PostCategory.checkColumnSize(obj.getName(), 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE post_category SET Name=? WHERE PostCategoryId=?;");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setInt(2, obj.getPostCategoryId());
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
            return obj;
        }
        
        public static void update(Integer PostCategoryId,String Name)
        {  
            try
            {   
                
                PostCategory.checkColumnSize(Name, 100);
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("post_category");
        }
        
        
        @Override
        public PostCategory getRelatedInfo(PostCategory post_category)
        {
              
            
            return post_category;
        }
        
        
        @Override
        public PostCategory getRelatedObjects(PostCategory post_category)
        {
            post_category.setBlogPostCategoryList(new BlogPostCategoryDaoImpl().findByColumn("PostCategoryId", post_category.getPostCategoryId().toString(),null,null));
             
            return post_category;
        }
        
        
        
        @Override
        public void remove(PostCategory obj)
        {            
            try
            {
                updateQuery("DELETE FROM post_category WHERE PostCategoryId=" + obj.getPostCategoryId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM post_category WHERE PostCategoryId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM post_category;");
            }
            catch (Exception ex)
            {
                System.out.println("PostCategory's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM post_category WHERE " + PostCategory.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("PostCategory's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public PostCategory getRelatedBlogPostCategoryList(PostCategory post_category)
        {           
            post_category.setBlogPostCategoryList(new BlogPostCategoryDaoImpl().findByColumn("PostCategoryId", post_category.getPostCategoryId().toString(),null,null));
            return post_category;
        }        
        
                             
    }

