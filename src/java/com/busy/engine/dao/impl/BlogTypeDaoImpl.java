





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object BlogType's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog_type;
        } 
    
        @Override
        public void add(BlogType obj)
        {
            try
            {
                
                BlogType.checkColumnSize(obj.getTypeName(), 100);
                                            
                openConnection();
                prepareStatement("INSERT INTO blog_type(TypeName) VALUES (?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("BlogType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String TypeName)
        {   
            int id = 0;
            try
            {
                
                BlogType.checkColumnSize(TypeName, 100);
                                            
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
                System.out.println("updateBlogType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer BlogTypeId,String TypeName)
        {  
            try
            {   
                
                BlogType.checkColumnSize(TypeName, 100);
                                  
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("blog_type");
        }
        
        
        @Override
        public BlogType getRelatedInfo(BlogType blog_type)
        {
              
            
            return blog_type;
        }
        
        
        @Override
        public BlogType getRelatedObjects(BlogType blog_type)
        {
            blog_type.setBlogList(new BlogDaoImpl().findByColumn("BlogTypeId", blog_type.getBlogTypeId().toString(),null,null));
             
            return blog_type;
        }
        
        
        
        @Override
        public void remove(BlogType obj)
        {            
            try
            {
                updateQuery("DELETE FROM blog_type WHERE BlogTypeId=" + obj.getBlogTypeId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM blog_type WHERE BlogTypeId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM blog_type;");
            }
            catch (Exception ex)
            {
                System.out.println("BlogType's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM blog_type WHERE " + BlogType.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("BlogType's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public BlogType getRelatedBlogList(BlogType blog_type)
        {           
            blog_type.setBlogList(new BlogDaoImpl().findByColumn("BlogTypeId", blog_type.getBlogTypeId().toString(),null,null));
            return blog_type;
        }        
        
                             
    }

