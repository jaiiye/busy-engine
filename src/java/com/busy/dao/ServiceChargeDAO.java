


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class ServiceChargeDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ServiceCharge.PROP_SERVICE_CHARGE_ID) || column.equals(ServiceCharge.PROP_CHARGE_NAME) || column.equals(ServiceCharge.PROP_DESCRIPTION) || column.equals(ServiceCharge.PROP_RATE) || column.equals(ServiceCharge.PROP_UNITS) || column.equals(ServiceCharge.PROP_DATE) || column.equals(ServiceCharge.PROP_USER_SERVICE_ID) )
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
            if (column.equals(ServiceCharge.PROP_SERVICE_CHARGE_ID) || column.equals(ServiceCharge.PROP_USER_SERVICE_ID) )
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
            return new ServiceCharge(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getInt(7));
        }
        
        public static int addServiceCharge(String ChargeName, String Description, String Rate, String Units, Date Date, Integer UserServiceId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(ChargeName, 100);
                checkColumnSize(Description, 65535);
                checkColumnSize(Rate, 12);
                checkColumnSize(Units, 12);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO service_charge(ChargeName,Description,Rate,Units,Date,UserServiceId) VALUES (?,?,?,?,?,?);");                    
                preparedStatement.setString(1, ChargeName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, Rate);
                preparedStatement.setString(4, Units);
                preparedStatement.setDate(5, new java.sql.Date(Date.getTime()));
                preparedStatement.setInt(6, UserServiceId);
                
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
                
        public static void updateServiceCharge(Integer ServiceChargeId,String ChargeName,String Description,String Rate,String Units,Date Date,Integer UserServiceId)
        {  
            try
            {   
                
                checkColumnSize(ChargeName, 100);
                checkColumnSize(Description, 65535);
                checkColumnSize(Rate, 12);
                checkColumnSize(Units, 12);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE service_charge SET ChargeName=?,Description=?,Rate=?,Units=?,Date=?,UserServiceId=? WHERE ServiceChargeId=?;");                    
                preparedStatement.setString(1, ChargeName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, Rate);
                preparedStatement.setString(4, Units);
                preparedStatement.setDate(5, new java.sql.Date(Date.getTime()));
                preparedStatement.setInt(6, UserServiceId);
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

