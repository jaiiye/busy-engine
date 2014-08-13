





































    package com.busy.engine.dao;

import com.busy.engine.entity.PostCategory;
    import com.busy.engine.data.BasicConnection;
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
                System.out.println("PostCategory's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return post_category;
        } 
    
        @Override
        public int add(PostCategory obj)
        {
            int id = 0;
            try
            {
                
                PostCategory.checkColumnSize(obj.getCategoryName(), 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO post_category(CategoryName) VALUES (?);");                    
                preparedStatement.setString(1, obj.getCategoryName());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from post_category;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("PostCategory's add method error: " + ex.getMessage());
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
                
                PostCategory.checkColumnSize(obj.getCategoryName(), 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE post_category SET CategoryName=? WHERE PostCategoryId=?;");                    
                preparedStatement.setString(1, obj.getCategoryName());
                preparedStatement.setInt(2, obj.getPostCategoryId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("PostCategory's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("post_category");
        }
        
        
        @Override
        public void getRelatedInfo(PostCategory post_category)
        {
              
        }
        
        @Override
        public void getRelatedObjects(PostCategory post_category)
        {
            post_category.setBlogPostCategoryList(new BlogPostCategoryDaoImpl().findByColumn("PostCategoryId", post_category.getPostCategoryId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(PostCategory obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM post_category WHERE PostCategoryId=" + obj.getPostCategoryId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("PostCategory's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM post_category WHERE PostCategoryId=" + id + ";");           
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
                updateQuery("DELETE FROM post_category;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("PostCategory's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM post_category WHERE " + PostCategory.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("PostCategory's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedBlogPostCategoryList(PostCategory post_category)
        {           
            post_category.setBlogPostCategoryList(new BlogPostCategoryDaoImpl().findByColumn("PostCategoryId", post_category.getPostCategoryId().toString(),null,null));
        }        
        
                             
    }

