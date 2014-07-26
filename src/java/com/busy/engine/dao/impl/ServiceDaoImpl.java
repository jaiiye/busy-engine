





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
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
                System.out.println("Object Service's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service;
        } 
    
        @Override
        public void add(Service obj)
        {
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
            }
            catch (Exception ex)
            {
                System.out.println("Service's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String ServiceName, String Description, Integer ServiceStatus, Integer ServiceChargeId, Integer ServiceTypeId)
        {   
            int id = 0;
            try
            {
                
                Service.checkColumnSize(ServiceName, 100);
                Service.checkColumnSize(Description, 65535);
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO service(ServiceName,Description,ServiceStatus,ServiceChargeId,ServiceTypeId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, ServiceName);
                preparedStatement.setString(2, Description);
                preparedStatement.setInt(3, ServiceStatus);
                preparedStatement.setInt(4, ServiceChargeId);
                preparedStatement.setInt(5, ServiceTypeId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from service;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addService error: " + ex.getMessage());
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
                System.out.println("updateService error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        public static void update(Integer ServiceId,String ServiceName,String Description,Integer ServiceStatus,Integer ServiceChargeId,Integer ServiceTypeId)
        {  
            try
            {   
                
                Service.checkColumnSize(ServiceName, 100);
                Service.checkColumnSize(Description, 65535);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE service SET ServiceName=?,Description=?,ServiceStatus=?,ServiceChargeId=?,ServiceTypeId=? WHERE ServiceId=?;");                    
                preparedStatement.setString(1, ServiceName);
                preparedStatement.setString(2, Description);
                preparedStatement.setInt(3, ServiceStatus);
                preparedStatement.setInt(4, ServiceChargeId);
                preparedStatement.setInt(5, ServiceTypeId);
                preparedStatement.setInt(6, ServiceId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateService error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("service");
        }
        
        
        @Override
        public Service getRelatedInfo(Service service)
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
              
            
            return service;
        }
        
        
        @Override
        public Service getRelatedObjects(Service service)
        {
            service.setUserServiceList(new UserServiceDaoImpl().findByColumn("ServiceId", service.getServiceId().toString(),null,null));
             
            return service;
        }
        
        
        
        @Override
        public void remove(Service obj)
        {            
            try
            {
                updateQuery("DELETE FROM service WHERE ServiceId=" + obj.getServiceId() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteServiceById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service WHERE ServiceId=" + id.intValue() + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteServiceById error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service;");
            }
            catch (Exception ex)
            {
                System.out.println("Service's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service WHERE " + Service.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Service's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Service getRelatedUserServiceList(Service service)
        {           
            service.setUserServiceList(new UserServiceDaoImpl().findByColumn("ServiceId", service.getServiceId().toString(),null,null));
            return service;
        }        
        
                             
    }

