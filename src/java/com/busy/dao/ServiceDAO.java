





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ServiceDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Service.PROP_SERVICE_ID) || column.equals(Service.PROP_SERVICE_NAME) || column.equals(Service.PROP_DESCRIPTION) || column.equals(Service.PROP_SERVICE_STATUS) || column.equals(Service.PROP_SERVICE_CHARGE_ID) || column.equals(Service.PROP_SERVICE_TYPE_ID) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(Service.PROP_SERVICE_ID) || column.equals(Service.PROP_SERVICE_STATUS) || column.equals(Service.PROP_SERVICE_CHARGE_ID) || column.equals(Service.PROP_SERVICE_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Service processService(ResultSet rs) throws SQLException
        {        
            return new Service(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
        }
        
        public static int addService(String ServiceName, String Description, Integer ServiceStatus, Integer ServiceChargeId, Integer ServiceTypeId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(ServiceName, 100);
                checkColumnSize(Description, 65535);
                
                
                
                                            
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
        
        public static int getAllServiceCount()
        {
            return getAllRecordsCountByTableName("service");        
        }
        
        public static ArrayList<Service> getAllService()
        {
            ArrayList<Service> service = new ArrayList<Service>();
            try
            {
                getAllRecordsByTableName("service");
                while(rs.next())
                {
                    service.add(processService(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllService error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service;
        }
        
        public static ArrayList<Service> getAllServiceWithRelatedInfo()
        {
            ArrayList<Service> serviceList = new ArrayList<Service>();
            try
            {
                getAllRecordsByTableName("service");
                while (rs.next())
                {
                    serviceList.add(processService(rs));
                }

                
                    for(Service service : serviceList)
                    {
                        
                            getRecordById("ServiceCharge", service.getServiceChargeId().toString());
                            service.setServiceCharge(ServiceChargeDAO.processServiceCharge(rs));               
                        
                            getRecordById("ServiceType", service.getServiceTypeId().toString());
                            service.setServiceType(ServiceTypeDAO.processServiceType(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("getAllServiceWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return serviceList;
        }
        
        
        public static Service getRelatedInfo(Service service)
        {
           
                
                    try
                    { 
                        
                            getRecordById("ServiceCharge", service.getServiceChargeId().toString());
                            service.setServiceCharge(ServiceChargeDAO.processServiceCharge(rs));               
                        
                            getRecordById("ServiceType", service.getServiceTypeId().toString());
                            service.setServiceType(ServiceTypeDAO.processServiceType(rs));               
                        

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
        
        public static Service getAllRelatedObjects(Service service)
        {           
            service.setUserServiceList(UserServiceDAO.getAllUserServiceByColumn("ServiceId", service.getServiceId().toString()));
             
            return service;
        }
        
        
                    
        public static Service getRelatedUserServiceList(Service service)
        {           
            service.setUserServiceList(UserServiceDAO.getAllUserServiceByColumn("ServiceId", service.getServiceId().toString()));
            return service;
        }        
        
                
        public static ArrayList<Service> getServicePaged(int limit, int offset)
        {
            ArrayList<Service> service = new ArrayList<Service>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("service", limit, offset);
                while (rs.next())
                {
                    service.add(processService(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getServicePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service;
        } 
        
        public static ArrayList<Service> getAllServiceByColumn(String columnName, String columnValue)
        {
            ArrayList<Service> service = new ArrayList<Service>();
            try
            {
                getAllRecordsByColumn("service", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    service.add(processService(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllServiceByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service;
        }
        
        public static Service getServiceByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Service service = new Service();
            try
            {
                getRecordsByColumnWithLimitAndOffset("service", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   service = processService(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getServiceByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service;
        }                
                
        public static void updateService(Integer ServiceId,String ServiceName,String Description,Integer ServiceStatus,Integer ServiceChargeId,Integer ServiceTypeId)
        {  
            try
            {   
                
                checkColumnSize(ServiceName, 100);
                checkColumnSize(Description, 65535);
                
                
                
                                  
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
        
        public static void deleteAllService()
        {
            try
            {
                updateQuery("DELETE FROM service;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllService error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteServiceById(String id)
        {
            try
            {
                updateQuery("DELETE FROM service WHERE ServiceId=" + id + ";");            
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

        public static void deleteServiceByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM service WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteServiceByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

