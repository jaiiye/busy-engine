


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ServiceCharge extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SERVICECHARGEID = "ServiceChargeId";
        public static final String PROP_SERVICECHARGENAME = "ServiceChargeName";
        public static final String PROP_SERVICECHARGEDESCRIPTION = "ServiceChargeDescription";
        public static final String PROP_SERVICECHARGERATE = "ServiceChargeRate";
        public static final String PROP_SERVICECHARGEUNITS = "ServiceChargeUnits";
        public static final String PROP_USERSERVICEID = "UserServiceId";
        public static final String PROP_SERVICECHARGEDATE = "ServiceChargeDate";
        
        
        private Integer serviceChargeId;
        private String serviceChargeName;
        private String serviceChargeDescription;
        private String serviceChargeRate;
        private String serviceChargeUnits;
        private Integer userServiceId;
        private Date serviceChargeDate;
        
        
        public ServiceCharge()
        {
            this.serviceChargeId = 0; 
       this.serviceChargeName = ""; 
       this.serviceChargeDescription = ""; 
       this.serviceChargeRate = ""; 
       this.serviceChargeUnits = ""; 
       this.userServiceId = 0; 
       this.serviceChargeDate = null; 
        }
        
        public ServiceCharge(Integer ServiceChargeId, String ServiceChargeName, String ServiceChargeDescription, String ServiceChargeRate, String ServiceChargeUnits, Integer UserServiceId, Date ServiceChargeDate)
        {
            this.serviceChargeId = ServiceChargeId;
       this.serviceChargeName = ServiceChargeName;
       this.serviceChargeDescription = ServiceChargeDescription;
       this.serviceChargeRate = ServiceChargeRate;
       this.serviceChargeUnits = ServiceChargeUnits;
       this.userServiceId = UserServiceId;
       this.serviceChargeDate = ServiceChargeDate;
        } 
        
        
            public Integer getServiceChargeId()
            {
                return this.serviceChargeId;
            }
            
            public void setServiceChargeId(Integer ServiceChargeId)
            {
                this.serviceChargeId = ServiceChargeId;
            }
        
            public String getServiceChargeName()
            {
                return this.serviceChargeName;
            }
            
            public void setServiceChargeName(String ServiceChargeName)
            {
                this.serviceChargeName = ServiceChargeName;
            }
        
            public String getServiceChargeDescription()
            {
                return this.serviceChargeDescription;
            }
            
            public void setServiceChargeDescription(String ServiceChargeDescription)
            {
                this.serviceChargeDescription = ServiceChargeDescription;
            }
        
            public String getServiceChargeRate()
            {
                return this.serviceChargeRate;
            }
            
            public void setServiceChargeRate(String ServiceChargeRate)
            {
                this.serviceChargeRate = ServiceChargeRate;
            }
        
            public String getServiceChargeUnits()
            {
                return this.serviceChargeUnits;
            }
            
            public void setServiceChargeUnits(String ServiceChargeUnits)
            {
                this.serviceChargeUnits = ServiceChargeUnits;
            }
        
            public Integer getUserServiceId()
            {
                return this.userServiceId;
            }
            
            public void setUserServiceId(Integer UserServiceId)
            {
                this.userServiceId = UserServiceId;
            }
        
            public Date getServiceChargeDate()
            {
                return this.serviceChargeDate;
            }
            
            public void setServiceChargeDate(Date ServiceChargeDate)
            {
                this.serviceChargeDate = ServiceChargeDate;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ServiceCharge.PROP_SERVICECHARGEID) || column.equals(ServiceCharge.PROP_SERVICECHARGENAME) || column.equals(ServiceCharge.PROP_SERVICECHARGEDESCRIPTION) || column.equals(ServiceCharge.PROP_SERVICECHARGERATE) || column.equals(ServiceCharge.PROP_SERVICECHARGEUNITS) || column.equals(ServiceCharge.PROP_USERSERVICEID) || column.equals(ServiceCharge.PROP_SERVICECHARGEDATE) )
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
            if (column.equals(ServiceCharge.PROP_SERVICECHARGEID) || column.equals(ServiceCharge.PROP_USERSERVICEID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ServiceCharge processServiceCharge(ResultSet rs) throws SQLException
        {        
            return new ServiceCharge(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDate(7));
        }
        
        public static int addServiceCharge(String ServiceChargeName, String ServiceChargeDescription, String ServiceChargeRate, String ServiceChargeUnits, Integer UserServiceId, Date ServiceChargeDate)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(ServiceChargeName, 100);
                checkColumnSize(ServiceChargeDescription, 65535);
                checkColumnSize(ServiceChargeRate, 12);
                checkColumnSize(ServiceChargeUnits, 12);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO service_charge(ServiceChargeName,ServiceChargeDescription,ServiceChargeRate,ServiceChargeUnits,UserServiceId,ServiceChargeDate) VALUES (?,?,?,?,?,?);");                    
                preparedStatement.setString(1, ServiceChargeName);
                preparedStatement.setString(2, ServiceChargeDescription);
                preparedStatement.setString(3, ServiceChargeRate);
                preparedStatement.setString(4, ServiceChargeUnits);
                preparedStatement.setInt(5, UserServiceId);
                preparedStatement.setDate(6, new java.sql.Date(ServiceChargeDate.getTime()));
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from service_charge;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addServiceCharge error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllServiceChargeCount()
        {
            return getAllRecordsCountByTableName("service_charge");        
        }
        
        public static ArrayList<ServiceCharge> getAllServiceCharge()
        {
            ArrayList<ServiceCharge> service_charge = new ArrayList<ServiceCharge>();
            try
            {
                getAllRecordsByTableName("service_charge");
                while(rs.next())
                {
                    service_charge.add(processServiceCharge(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllServiceCharge error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_charge;
        }
                
        public static ArrayList<ServiceCharge> getServiceChargePaged(int limit, int offset)
        {
            ArrayList<ServiceCharge> service_charge = new ArrayList<ServiceCharge>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("service_charge", limit, offset);
                while (rs.next())
                {
                    service_charge.add(processServiceCharge(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getServiceChargePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_charge;
        } 
        
        public static ArrayList<ServiceCharge> getAllServiceChargeByColumn(String columnName, String columnValue)
        {
            ArrayList<ServiceCharge> service_charge = new ArrayList<ServiceCharge>();
            try
            {
                getAllRecordsByColumn("service_charge", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    service_charge.add(processServiceCharge(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllServiceChargeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_charge;
        }
        
        public static ServiceCharge getServiceChargeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ServiceCharge service_charge = new ServiceCharge();
            try
            {
                getRecordsByColumnWithLimitAndOffset("service_charge", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   service_charge = processServiceCharge(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getServiceChargeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_charge;
        }                
                
        public static void updateServiceCharge(Integer ServiceChargeId,String ServiceChargeName,String ServiceChargeDescription,String ServiceChargeRate,String ServiceChargeUnits,Integer UserServiceId,Date ServiceChargeDate)
        {  
            try
            {   
                
                checkColumnSize(ServiceChargeName, 100);
                checkColumnSize(ServiceChargeDescription, 65535);
                checkColumnSize(ServiceChargeRate, 12);
                checkColumnSize(ServiceChargeUnits, 12);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE service_charge SET ServiceChargeName=?,ServiceChargeDescription=?,ServiceChargeRate=?,ServiceChargeUnits=?,UserServiceId=?,ServiceChargeDate=? WHERE ServiceChargeId=?;");                    
                preparedStatement.setString(1, ServiceChargeName);
                preparedStatement.setString(2, ServiceChargeDescription);
                preparedStatement.setString(3, ServiceChargeRate);
                preparedStatement.setString(4, ServiceChargeUnits);
                preparedStatement.setInt(5, UserServiceId);
                preparedStatement.setDate(6, new java.sql.Date(ServiceChargeDate.getTime()));
                preparedStatement.setInt(7, ServiceChargeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateServiceCharge error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllServiceCharge()
        {
            try
            {
                updateQuery("DELETE FROM service_charge;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllServiceCharge error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteServiceChargeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM service_charge WHERE ServiceChargeId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteServiceChargeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteServiceChargeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM service_charge WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteServiceChargeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

