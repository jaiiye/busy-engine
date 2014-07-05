


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class Service extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SERVICEID = "ServiceId";
        public static final String PROP_SERVICETYPEID = "ServiceTypeId";
        public static final String PROP_SERVICECHARGEID = "ServiceChargeId";
        public static final String PROP_SERVICENAME = "ServiceName";
        public static final String PROP_SERVICEDESCRIPTION = "ServiceDescription";
        
        
        private Integer serviceId;
        private Integer serviceTypeId;
        private Integer serviceChargeId;
        private String serviceName;
        private String serviceDescription;
        
        
        public Service()
        {
            this.serviceId = 0; 
       this.serviceTypeId = 0; 
       this.serviceChargeId = 0; 
       this.serviceName = ""; 
       this.serviceDescription = ""; 
        }
        
        public Service(Integer ServiceId, Integer ServiceTypeId, Integer ServiceChargeId, String ServiceName, String ServiceDescription)
        {
            this.serviceId = ServiceId;
       this.serviceTypeId = ServiceTypeId;
       this.serviceChargeId = ServiceChargeId;
       this.serviceName = ServiceName;
       this.serviceDescription = ServiceDescription;
        } 
        
        
            public Integer getServiceId()
            {
                return this.serviceId;
            }
            
            public void setServiceId(Integer ServiceId)
            {
                this.serviceId = ServiceId;
            }
        
            public Integer getServiceTypeId()
            {
                return this.serviceTypeId;
            }
            
            public void setServiceTypeId(Integer ServiceTypeId)
            {
                this.serviceTypeId = ServiceTypeId;
            }
        
            public Integer getServiceChargeId()
            {
                return this.serviceChargeId;
            }
            
            public void setServiceChargeId(Integer ServiceChargeId)
            {
                this.serviceChargeId = ServiceChargeId;
            }
        
            public String getServiceName()
            {
                return this.serviceName;
            }
            
            public void setServiceName(String ServiceName)
            {
                this.serviceName = ServiceName;
            }
        
            public String getServiceDescription()
            {
                return this.serviceDescription;
            }
            
            public void setServiceDescription(String ServiceDescription)
            {
                this.serviceDescription = ServiceDescription;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Service.PROP_SERVICEID) || column.equals(Service.PROP_SERVICETYPEID) || column.equals(Service.PROP_SERVICECHARGEID) || column.equals(Service.PROP_SERVICENAME) || column.equals(Service.PROP_SERVICEDESCRIPTION) )
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
            if (column.equals(Service.PROP_SERVICEID) || column.equals(Service.PROP_SERVICETYPEID) || column.equals(Service.PROP_SERVICECHARGEID) )
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
            return new Service(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
        }
        
        public static int addService(Integer ServiceTypeId, Integer ServiceChargeId, String ServiceName, String ServiceDescription)
        {   
            int id = 0;
            try
            {
                
                
                
                checkColumnSize(ServiceName, 100);
                checkColumnSize(ServiceDescription, 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO service(ServiceTypeId,ServiceChargeId,ServiceName,ServiceDescription) VALUES (?,?,?,?);");                    
                preparedStatement.setInt(1, ServiceTypeId);
                preparedStatement.setInt(2, ServiceChargeId);
                preparedStatement.setString(3, ServiceName);
                preparedStatement.setString(4, ServiceDescription);
                
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
                
        public static void updateService(Integer ServiceId,Integer ServiceTypeId,Integer ServiceChargeId,String ServiceName,String ServiceDescription)
        {  
            try
            {   
                
                
                
                checkColumnSize(ServiceName, 100);
                checkColumnSize(ServiceDescription, 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE service SET ServiceTypeId=?,ServiceChargeId=?,ServiceName=?,ServiceDescription=? WHERE ServiceId=?;");                    
                preparedStatement.setInt(1, ServiceTypeId);
                preparedStatement.setInt(2, ServiceChargeId);
                preparedStatement.setString(3, ServiceName);
                preparedStatement.setString(4, ServiceDescription);
                preparedStatement.setInt(5, ServiceId);
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

