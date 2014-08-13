





































    package com.busy.engine.dao;

import com.busy.engine.entity.ServiceType;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ServiceTypeDaoImpl extends BasicConnection implements Serializable, ServiceTypeDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public ServiceType find(Integer id)
        {
            return findByColumn("ServiceTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ServiceType> findAll(Integer limit, Integer offset)
        {
            ArrayList<ServiceType> service_type = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("service_type");
                while(rs.next())
                {
                    service_type.add(ServiceType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ServiceType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_type;
         
        }
        
        @Override
        public ArrayList<ServiceType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ServiceType> service_typeList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("service_type", limit, offset);
                while (rs.next())
                {
                    service_typeList.add(ServiceType.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object ServiceType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_typeList;
        }
        
        @Override
        public ArrayList<ServiceType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ServiceType> service_type = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("service_type", ServiceType.checkColumnName(columnName), columnValue, ServiceType.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   service_type.add(ServiceType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ServiceType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_type;
        } 
    
        @Override
        public int add(ServiceType obj)
        {
            int id = 0;
            try
            {
                
                ServiceType.checkColumnSize(obj.getTypeName(), 100);
                ServiceType.checkColumnSize(obj.getDesciption(), 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO service_type(TypeName,Desciption) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDesciption());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from service_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("ServiceType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public ServiceType update(ServiceType obj)
        {
           try
            {   
                
                ServiceType.checkColumnSize(obj.getTypeName(), 100);
                ServiceType.checkColumnSize(obj.getDesciption(), 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE service_type SET TypeName=?,Desciption=? WHERE ServiceTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDesciption());
                preparedStatement.setInt(3, obj.getServiceTypeId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("ServiceType's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("service_type");
        }
        
        
        @Override
        public void getRelatedInfo(ServiceType service_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ServiceType service_type)
        {
            service_type.setServiceList(new ServiceDaoImpl().findByColumn("ServiceTypeId", service_type.getServiceTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ServiceType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM service_type WHERE ServiceTypeId=" + obj.getServiceTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ServiceType's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service_type WHERE ServiceTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM service_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ServiceType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service_type WHERE " + ServiceType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ServiceType's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedServiceList(ServiceType service_type)
        {           
            service_type.setServiceList(new ServiceDaoImpl().findByColumn("ServiceTypeId", service_type.getServiceTypeId().toString(),null,null));
        }        
        
                             
    }

