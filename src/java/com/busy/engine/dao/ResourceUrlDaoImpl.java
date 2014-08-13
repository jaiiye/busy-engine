





































    package com.busy.engine.dao;

import com.busy.engine.entity.ResourceType;
import com.busy.engine.entity.ResourceUrl;
import com.busy.engine.entity.Template;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ResourceUrlDaoImpl extends BasicConnection implements Serializable, ResourceUrlDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ResourceUrl find(Integer id)
        {
            return findByColumn("ResourceUrlId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ResourceUrl> findAll(Integer limit, Integer offset)
        {
            ArrayList<ResourceUrl> resource_url = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("resource_url");
                while(rs.next())
                {
                    resource_url.add(ResourceUrl.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ResourceUrl object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_url;
         
        }
        
        @Override
        public ArrayList<ResourceUrl> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ResourceUrl> resource_urlList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("resource_url", limit, offset);
                while (rs.next())
                {
                    resource_urlList.add(ResourceUrl.process(rs));
                }

                
                    for(ResourceUrl resource_url : resource_urlList)
                    {
                        
                            getRecordById("Template", resource_url.getTemplateId().toString());
                            resource_url.setTemplate(Template.process(rs));               
                        
                            getRecordById("ResourceType", resource_url.getResourceTypeId().toString());
                            resource_url.setResourceType(ResourceType.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object ResourceUrl method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_urlList;
        }
        
        @Override
        public ArrayList<ResourceUrl> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ResourceUrl> resource_url = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("resource_url", ResourceUrl.checkColumnName(columnName), columnValue, ResourceUrl.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   resource_url.add(ResourceUrl.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ResourceUrl's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_url;
        } 
    
        @Override
        public int add(ResourceUrl obj)
        {
            int id = 0;
            try
            {
                
                ResourceUrl.checkColumnSize(obj.getUrl(), 255);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO resource_url(Url,TemplateId,ResourceTypeId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getUrl());
                preparedStatement.setInt(2, obj.getTemplateId());
                preparedStatement.setInt(3, obj.getResourceTypeId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from resource_url;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ResourceUrl's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public ResourceUrl update(ResourceUrl obj)
        {
           try
            {   
                
                ResourceUrl.checkColumnSize(obj.getUrl(), 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE resource_url SET Url=?,TemplateId=?,ResourceTypeId=? WHERE ResourceUrlId=?;");                    
                preparedStatement.setString(1, obj.getUrl());
                preparedStatement.setInt(2, obj.getTemplateId());
                preparedStatement.setInt(3, obj.getResourceTypeId());
                preparedStatement.setInt(4, obj.getResourceUrlId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("ResourceUrl's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("resource_url");
        }
        
        
        @Override
        public void getRelatedInfo(ResourceUrl resource_url)
        {
            
                try
                { 
                    
                            getRecordById("Template", resource_url.getTemplateId().toString());
                            resource_url.setTemplate(Template.process(rs));                                       
                    
                            getRecordById("ResourceType", resource_url.getResourceTypeId().toString());
                            resource_url.setResourceType(ResourceType.process(rs));                                       
                    
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
        public void getRelatedObjects(ResourceUrl resource_url)
        {
             
        }
        
        @Override
        public boolean remove(ResourceUrl obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM resource_url WHERE ResourceUrlId=" + obj.getResourceUrlId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ResourceUrl's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM resource_url WHERE ResourceUrlId=" + id + ";");           
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
                updateQuery("DELETE FROM resource_url;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ResourceUrl's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM resource_url WHERE " + ResourceUrl.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ResourceUrl's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        
                             
    }

