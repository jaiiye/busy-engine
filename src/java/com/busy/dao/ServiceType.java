


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ServiceType extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_SERVICETYPEID = "ServiceTypeId";
        public static final String PROP_SERVICETYPENAME = "ServiceTypeName";
        public static final String PROP_SERVICETYPEDESCIPTION = "ServiceTypeDesciption";
        
        
        private Integer serviceTypeId;
        private String serviceTypeName;
        private String serviceTypeDesciption;
        
        
        public ServiceType()
        {
            this.serviceTypeId = 0; 
       this.serviceTypeName = ""; 
       this.serviceTypeDesciption = ""; 
        }
        
        public ServiceType(Integer ServiceTypeId, String ServiceTypeName, String ServiceTypeDesciption)
        {
            this.serviceTypeId = ServiceTypeId;
       this.serviceTypeName = ServiceTypeName;
       this.serviceTypeDesciption = ServiceTypeDesciption;
        } 
        
        
            public Integer getServiceTypeId()
            {
                return this.serviceTypeId;
            }
            
            public void setServiceTypeId(Integer ServiceTypeId)
            {
                this.serviceTypeId = ServiceTypeId;
            }
        
            public String getServiceTypeName()
            {
                return this.serviceTypeName;
            }
            
            public void setServiceTypeName(String ServiceTypeName)
            {
                this.serviceTypeName = ServiceTypeName;
            }
        
            public String getServiceTypeDesciption()
            {
                return this.serviceTypeDesciption;
            }
            
            public void setServiceTypeDesciption(String ServiceTypeDesciption)
            {
                this.serviceTypeDesciption = ServiceTypeDesciption;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ServiceType.PROP_SERVICETYPEID) || column.equals(ServiceType.PROP_SERVICETYPENAME) || column.equals(ServiceType.PROP_SERVICETYPEDESCIPTION) )
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
            if (column.equals(ServiceType.PROP_SERVICETYPEID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ServiceType processServiceType(ResultSet rs) throws SQLException
        {        
            return new ServiceType(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        
        public static int addServiceType(String ServiceTypeName, String ServiceTypeDesciption)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(ServiceTypeName, 100);
                checkColumnSize(ServiceTypeDesciption, 65535);
                                            
                openConnection();
                prepareStatement("INSERT INTO service_type(ServiceTypeName,ServiceTypeDesciption) VALUES (?,?);");                    
                preparedStatement.setString(1, ServiceTypeName);
                preparedStatement.setString(2, ServiceTypeDesciption);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from service_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addServiceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllServiceTypeCount()
        {
            return getAllRecordsCountByTableName("service_type");        
        }
        
        public static ArrayList<ServiceType> getAllServiceType()
        {
            ArrayList<ServiceType> service_type = new ArrayList<ServiceType>();
            try
            {
                getAllRecordsByTableName("service_type");
                while(rs.next())
                {
                    service_type.add(processServiceType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllServiceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_type;
        }
                
        public static ArrayList<ServiceType> getServiceTypePaged(int limit, int offset)
        {
            ArrayList<ServiceType> service_type = new ArrayList<ServiceType>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("service_type", limit, offset);
                while (rs.next())
                {
                    service_type.add(processServiceType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getServiceTypePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_type;
        } 
        
        public static ArrayList<ServiceType> getAllServiceTypeByColumn(String columnName, String columnValue)
        {
            ArrayList<ServiceType> service_type = new ArrayList<ServiceType>();
            try
            {
                getAllRecordsByColumn("service_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    service_type.add(processServiceType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllServiceTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_type;
        }
        
        public static ServiceType getServiceTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            ServiceType service_type = new ServiceType();
            try
            {
                getRecordsByColumnWithLimitAndOffset("service_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   service_type = processServiceType(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getServiceTypeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service_type;
        }                
                
        public static void updateServiceType(Integer ServiceTypeId,String ServiceTypeName,String ServiceTypeDesciption)
        {  
            try
            {   
                
                checkColumnSize(ServiceTypeName, 100);
                checkColumnSize(ServiceTypeDesciption, 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE service_type SET ServiceTypeName=?,ServiceTypeDesciption=? WHERE ServiceTypeId=?;");                    
                preparedStatement.setString(1, ServiceTypeName);
                preparedStatement.setString(2, ServiceTypeDesciption);
                preparedStatement.setInt(3, ServiceTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateServiceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllServiceType()
        {
            try
            {
                updateQuery("DELETE FROM service_type;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllServiceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteServiceTypeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM service_type WHERE ServiceTypeId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteServiceTypeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteServiceTypeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM service_type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteServiceTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

