





































    package com.busy.engine.dao;

import com.busy.engine.entity.BlogType;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class BlogTypeDaoImpl extends BasicConnection implements Serializable, BlogTypeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public BlogType find(Integer id)
        {
            return findByColumn("BlogTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<BlogType> findAll(Integer limit, Integer offset)
        {
            ArrayList<BlogType> blog_type = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("blog_type");
                while(rs.next())
                {
                    blog_type.add(BlogType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("BlogType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_type;
         
        }
        
        @Override
        public ArrayList<BlogType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<BlogType> blog_typeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("blog_type", limit, offset);
                while (rs.next())
                {
                    blog_typeList.add(BlogType.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object BlogType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_typeList;
        }
        
        @Override
        public ArrayList<BlogType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<BlogType> blog_type = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("blog_type", BlogType.checkColumnName(columnName), columnValue, BlogType.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   blog_type.add(BlogType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("BlogType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_type;
        } 
    
        @Override
        public int add(BlogType obj)
        {
            int id = 0;
            try
            {
                
                BlogType.checkColumnSize(obj.getTypeName(), 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO blog_type(TypeName) VALUES (?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from blog_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("BlogType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public BlogType update(BlogType obj)
        {
           try
            {   
                
                BlogType.checkColumnSize(obj.getTypeName(), 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE blog_type SET TypeName=? WHERE BlogTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setInt(2, obj.getBlogTypeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("BlogType's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("blog_type");
        }
        
        
        @Override
        public void getRelatedInfo(BlogType blog_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(BlogType blog_type)
        {
            blog_type.setBlogList(new BlogDaoImpl().findByColumn("BlogTypeId", blog_type.getBlogTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(BlogType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM blog_type WHERE BlogTypeId=" + obj.getBlogTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogType's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM blog_type WHERE BlogTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM blog_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM blog_type WHERE " + BlogType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("BlogType's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedBlogList(BlogType blog_type)
        {           
            blog_type.setBlogList(new BlogDaoImpl().findByColumn("BlogTypeId", blog_type.getBlogTypeId().toString(),null,null));
        }        
        
                             
    }

