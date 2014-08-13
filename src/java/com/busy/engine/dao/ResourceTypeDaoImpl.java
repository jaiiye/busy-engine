





































    package com.busy.engine.dao;

import com.busy.engine.entity.ResourceType;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ResourceTypeDaoImpl extends BasicConnection implements Serializable, ResourceTypeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ResourceType find(Integer id)
        {
            return findByColumn("ResourceTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ResourceType> findAll(Integer limit, Integer offset)
        {
            ArrayList<ResourceType> resource_type = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("resource_type");
                while(rs.next())
                {
                    resource_type.add(ResourceType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ResourceType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_type;
         
        }
        
        @Override
        public ArrayList<ResourceType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ResourceType> resource_typeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("resource_type", limit, offset);
                while (rs.next())
                {
                    resource_typeList.add(ResourceType.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object ResourceType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_typeList;
        }
        
        @Override
        public ArrayList<ResourceType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ResourceType> resource_type = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("resource_type", ResourceType.checkColumnName(columnName), columnValue, ResourceType.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   resource_type.add(ResourceType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ResourceType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_type;
        } 
    
        @Override
        public int add(ResourceType obj)
        {
            int id = 0;
            try
            {
                
                ResourceType.checkColumnSize(obj.getTypeName(), 45);
                ResourceType.checkColumnSize(obj.getTypeValue(), 150);
                                            
                openConnection();
                prepareStatement("INSERT INTO resource_type(TypeName,TypeValue) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getTypeValue());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from resource_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ResourceType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public ResourceType update(ResourceType obj)
        {
           try
            {   
                
                ResourceType.checkColumnSize(obj.getTypeName(), 45);
                ResourceType.checkColumnSize(obj.getTypeValue(), 150);
                                  
                openConnection();                           
                prepareStatement("UPDATE resource_type SET TypeName=?,TypeValue=? WHERE ResourceTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getTypeValue());
                preparedStatement.setInt(3, obj.getResourceTypeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("ResourceType's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("resource_type");
        }
        
        
        @Override
        public void getRelatedInfo(ResourceType resource_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ResourceType resource_type)
        {
            resource_type.setResourceUrlList(new ResourceUrlDaoImpl().findByColumn("ResourceTypeId", resource_type.getResourceTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ResourceType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM resource_type WHERE ResourceTypeId=" + obj.getResourceTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ResourceType's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM resource_type WHERE ResourceTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM resource_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ResourceType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM resource_type WHERE " + ResourceType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ResourceType's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedResourceUrlList(ResourceType resource_type)
        {           
            resource_type.setResourceUrlList(new ResourceUrlDaoImpl().findByColumn("ResourceTypeId", resource_type.getResourceTypeId().toString(),null,null));
        }        
        
                             
    }

