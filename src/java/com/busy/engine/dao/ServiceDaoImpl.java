





































    package com.busy.engine.dao;

import com.busy.engine.entity.Service;
import com.busy.engine.entity.ServiceCharge;
import com.busy.engine.entity.ServiceType;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ServiceDaoImpl extends BasicConnection implements Serializable, ServiceDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Service find(Integer id)
        {
            return findByColumn("ServiceId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Service> findAll(Integer limit, Integer offset)
        {
            ArrayList<Service> service = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("service");
                while(rs.next())
                {
                    service.add(Service.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Service object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service;
         
        }
        
        @Override
        public ArrayList<Service> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Service> serviceList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("service", limit, offset);
                while (rs.next())
                {
                    serviceList.add(Service.process(rs));
                }

                
                    for(Service service : serviceList)
                    {
                        
                            getRecordById("ServiceCharge", service.getServiceChargeId().toString());
                            service.setServiceCharge(ServiceCharge.process(rs));               
                        
                            getRecordById("ServiceType", service.getServiceTypeId().toString());
                            service.setServiceType(ServiceType.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object Service method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return serviceList;
        }
        
        @Override
        public ArrayList<Service> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Service> service = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("service", Service.checkColumnName(columnName), columnValue, Service.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   service.add(Service.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Service's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service;
        } 
    
        @Override
        public int add(Service obj)
        {
            int id = 0;
            try
            {
                
                Service.checkColumnSize(obj.getServiceName(), 100);
                Service.checkColumnSize(obj.getDescription(), 65535);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO service(ServiceName,Description,ServiceStatus,ServiceChargeId,ServiceTypeId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getServiceName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getServiceStatus());
                preparedStatement.setInt(4, obj.getServiceChargeId());
                preparedStatement.setInt(5, obj.getServiceTypeId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from service;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Service's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public Service update(Service obj)
        {
           try
            {   
                
                Service.checkColumnSize(obj.getServiceName(), 100);
                Service.checkColumnSize(obj.getDescription(), 65535);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE service SET ServiceName=?,Description=?,ServiceStatus=?,ServiceChargeId=?,ServiceTypeId=? WHERE ServiceId=?;");                    
                preparedStatement.setString(1, obj.getServiceName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getServiceStatus());
                preparedStatement.setInt(4, obj.getServiceChargeId());
                preparedStatement.setInt(5, obj.getServiceTypeId());
                preparedStatement.setInt(6, obj.getServiceId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("Service's update error: " + ex.getMessage());
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
            return getAllRecordsCountByTableName("service");
        }
        
        
        @Override
        public void getRelatedInfo(Service service)
        {
            
                try
                { 
                    
                            getRecordById("ServiceCharge", service.getServiceChargeId().toString());
                            service.setServiceCharge(ServiceCharge.process(rs));                                       
                    
                            getRecordById("ServiceType", service.getServiceTypeId().toString());
                            service.setServiceType(ServiceType.process(rs));                                       
                    
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
        public void getRelatedObjects(Service service)
        {
            service.setUserServiceList(new UserServiceDaoImpl().findByColumn("ServiceId", service.getServiceId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Service obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM service WHERE ServiceId=" + obj.getServiceId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Service's remove error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service WHERE ServiceId=" + id + ";");           
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
                updateQuery("DELETE FROM service;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Service's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service WHERE " + Service.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Service's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedUserServiceList(Service service)
        {           
            service.setUserServiceList(new UserServiceDaoImpl().findByColumn("ServiceId", service.getServiceId().toString(),null,null));
        }        
        
                             
    }

