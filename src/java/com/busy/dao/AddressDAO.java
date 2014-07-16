











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.Address;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class AddressDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Address.PROP_ADDRESS_ID) || column.equals(Address.PROP_RECIPIENT) || column.equals(Address.PROP_ADDRESS1) || column.equals(Address.PROP_ADDRESS2) || column.equals(Address.PROP_CITY) || column.equals(Address.PROP_STATE_PROVINCE) || column.equals(Address.PROP_ZIP_POSTAL_CODE) || column.equals(Address.PROP_COUNTRY) || column.equals(Address.PROP_REGION) || column.equals(Address.PROP_STATUS) || column.equals(Address.PROP_LOCALE) )
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
            if (column.equals(Address.PROP_ADDRESS_ID) || column.equals(Address.PROP_STATUS) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Address processAddress(ResultSet rs) throws SQLException
        {        
            return new Address(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11));
        }
        
        public static int addAddress(String Recipient, String Address1, String Address2, String City, String StateProvince, String ZipPostalCode, String Country, String Region, Integer Status, String Locale)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Recipient, 100);
                checkColumnSize(Address1, 255);
                checkColumnSize(Address2, 255);
                checkColumnSize(City, 100);
                checkColumnSize(StateProvince, 100);
                checkColumnSize(ZipPostalCode, 10);
                checkColumnSize(Country, 150);
                checkColumnSize(Region, 100);
                
                checkColumnSize(Locale, 10);
                                            
                openConnection();
                prepareStatement("INSERT INTO address(Recipient,Address1,Address2,City,StateProvince,ZipPostalCode,Country,Region,Status,Locale) VALUES (?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, Recipient);
                preparedStatement.setString(2, Address1);
                preparedStatement.setString(3, Address2);
                preparedStatement.setString(4, City);
                preparedStatement.setString(5, StateProvince);
                preparedStatement.setString(6, ZipPostalCode);
                preparedStatement.setString(7, Country);
                preparedStatement.setString(8, Region);
                preparedStatement.setInt(9, Status);
                preparedStatement.setString(10, Locale);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from address;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addAddress error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllAddressCount()
        {
            return getAllRecordsCountByTableName("address");        
        }
        
        public static ArrayList<Address> getAllAddress()
        {
            ArrayList<Address> address = new ArrayList<Address>();
            try
            {
                getAllRecordsByTableName("address");
                while(rs.next())
                {
                    address.add(processAddress(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllAddress error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return address;
        }
                
        public static ArrayList<Address> getAddressPaged(int limit, int offset)
        {
            ArrayList<Address> address = new ArrayList<Address>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("address", limit, offset);
                while (rs.next())
                {
                    address.add(processAddress(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAddressPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return address;
        } 
        
        public static ArrayList<Address> getAllAddressByColumn(String columnName, String columnValue)
        {
            ArrayList<Address> address = new ArrayList<Address>();
            try
            {
                getAllRecordsByColumn("address", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    address.add(processAddress(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllAddressByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return address;
        }
        
        public static Address getAddressByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Address address = new Address();
            try
            {
                getRecordsByColumnWithLimitAndOffset("address", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   address = processAddress(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAddressByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return address;
        }                
                
        public static void updateAddress(Integer AddressId,String Recipient,String Address1,String Address2,String City,String StateProvince,String ZipPostalCode,String Country,String Region,Integer Status,String Locale)
        {  
            try
            {   
                
                checkColumnSize(Recipient, 100);
                checkColumnSize(Address1, 255);
                checkColumnSize(Address2, 255);
                checkColumnSize(City, 100);
                checkColumnSize(StateProvince, 100);
                checkColumnSize(ZipPostalCode, 10);
                checkColumnSize(Country, 150);
                checkColumnSize(Region, 100);
                
                checkColumnSize(Locale, 10);
                                  
                openConnection();                           
                prepareStatement("UPDATE address SET Recipient=?,Address1=?,Address2=?,City=?,StateProvince=?,ZipPostalCode=?,Country=?,Region=?,Status=?,Locale=? WHERE AddressId=?;");                    
                preparedStatement.setString(1, Recipient);
                preparedStatement.setString(2, Address1);
                preparedStatement.setString(3, Address2);
                preparedStatement.setString(4, City);
                preparedStatement.setString(5, StateProvince);
                preparedStatement.setString(6, ZipPostalCode);
                preparedStatement.setString(7, Country);
                preparedStatement.setString(8, Region);
                preparedStatement.setInt(9, Status);
                preparedStatement.setString(10, Locale);
                preparedStatement.setInt(11, AddressId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateAddress error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllAddress()
        {
            try
            {
                updateQuery("DELETE FROM address;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllAddress error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAddressById(String id)
        {
            try
            {
                updateQuery("DELETE FROM address WHERE AddressId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteAddressById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteAddressByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM address WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteAddressByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

