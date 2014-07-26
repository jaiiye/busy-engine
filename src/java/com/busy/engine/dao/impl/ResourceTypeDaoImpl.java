





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object ResourceType's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resource_type;
        } 
    
        @Override
        public void add(ResourceType obj)
        {
            try
            {
                
                ResourceType.checkColumnSize(obj.getTypeName(), 45);
                ResourceType.checkColumnSize(obj.getTypeValue(), 150);
                                            
                openConnection();
                prepareStatement("INSERT INTO resource_type(TypeName,TypeValue) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getTypeValue());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("ResourceType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String TypeName, String TypeValue)
        {   
            int id = 0;
            try
            {
                
                ResourceType.checkColumnSize(TypeName, 45);
                ResourceType.checkColumnSize(TypeValue, 150);
                                            
                openConnection();
                prepareStatement("INSERT INTO resource_type(TypeName,TypeValue) VALUES (?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, TypeValue);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from resource_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addResourceType error: " + ex.getMessage());
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
                System.out.println("updateResourceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ResourceTypeId,String TypeName,String TypeValue)
        {  
            try
            {   
                
                ResourceType.checkColumnSize(TypeName, 45);
                ResourceType.checkColumnSize(TypeValue, 150);
                                  
                openConnection();                           
                prepareStatement("UPDATE resource_type SET TypeName=?,TypeValue=? WHERE ResourceTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, TypeValue);
                preparedStatement.setInt(3, ResourceTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateResourceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("resource_type");
        }
        
        
        @Override
        public ResourceType getRelatedInfo(ResourceType resource_type)
        {
              
            
            return resource_type;
        }
        
        
        @Override
        public ResourceType getRelatedObjects(ResourceType resource_type)
        {
            resource_type.setResourceUrlList(new ResourceUrlDaoImpl().findByColumn("ResourceTypeId", resource_type.getResourceTypeId().toString(),null,null));
             
            return resource_type;
        }
        
        
        
        @Override
        public void remove(ResourceType obj)
        {            
            try
            {
                updateQuery("DELETE FROM resource_type WHERE ResourceTypeId=" + obj.getResourceTypeId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteResourceTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM resource_type WHERE ResourceTypeId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteResourceTypeById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM resource_type;");
            }
            catch (Exception ex)
            {
                System.out.println("ResourceType's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM resource_type WHERE " + ResourceType.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("ResourceType's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public ResourceType getRelatedResourceUrlList(ResourceType resource_type)
        {           
            resource_type.setResourceUrlList(new ResourceUrlDaoImpl().findByColumn("ResourceTypeId", resource_type.getResourceTypeId().toString(),null,null));
            return resource_type;
        }        
        
                             
    }

