





































    package com.busy.engine.dao.impl;

    import com.transitionsoft.BasicConnection;
    import com.busy.engine.domain.*;
    import com.busy.engine.dao.base.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class AddressDaoImpl extends BasicConnection implements Serializable, AddressDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public Address find(Integer id)
        {
            return findByColumn("AddressId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Address> findAll(Integer limit, Integer offset)
        {
            ArrayList<Address> address = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("address");
                while(rs.next())
                {
                    address.add(Address.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Address object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return address;
         
        }
        
        @Override
        public ArrayList<Address> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Address> addressList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("address", limit, offset);
                while (rs.next())
                {
                    addressList.add(Address.process(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("Object Address method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return addressList;
        }
        
        @Override
        public ArrayList<Address> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Address> address = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("address", Address.checkColumnName(columnName), columnValue, Address.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   address.add(Address.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object Address's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return address;
        } 
    
        @Override
        public void add(Address obj)
        {
            try
            {
                
                Address.checkColumnSize(obj.getRecipient(), 100);
                Address.checkColumnSize(obj.getAddress1(), 255);
                Address.checkColumnSize(obj.getAddress2(), 255);
                Address.checkColumnSize(obj.getCity(), 100);
                Address.checkColumnSize(obj.getStateProvince(), 100);
                Address.checkColumnSize(obj.getZipPostalCode(), 10);
                Address.checkColumnSize(obj.getCountry(), 100);
                Address.checkColumnSize(obj.getRegion(), 100);
                
                Address.checkColumnSize(obj.getLocale(), 10);
                                            
                openConnection();
                prepareStatement("INSERT INTO address(Recipient,Address1,Address2,City,StateProvince,ZipPostalCode,Country,Region,AddressStatus,Locale) VALUES (?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getRecipient());
                preparedStatement.setString(2, obj.getAddress1());
                preparedStatement.setString(3, obj.getAddress2());
                preparedStatement.setString(4, obj.getCity());
                preparedStatement.setString(5, obj.getStateProvince());
                preparedStatement.setString(6, obj.getZipPostalCode());
                preparedStatement.setString(7, obj.getCountry());
                preparedStatement.setString(8, obj.getRegion());
                preparedStatement.setInt(9, obj.getAddressStatus());
                preparedStatement.setString(10, obj.getLocale());
                
                preparedStatement.executeUpdate();            
            }
            catch (Exception ex)
            {
                System.out.println("Address's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static int add(String Recipient, String Address1, String Address2, String City, String StateProvince, String ZipPostalCode, String Country, String Region, Integer AddressStatus, String Locale)
        {   
            int id = 0;
            try
            {
                
                Address.checkColumnSize(Recipient, 100);
                Address.checkColumnSize(Address1, 255);
                Address.checkColumnSize(Address2, 255);
                Address.checkColumnSize(City, 100);
                Address.checkColumnSize(StateProvince, 100);
                Address.checkColumnSize(ZipPostalCode, 10);
                Address.checkColumnSize(Country, 100);
                Address.checkColumnSize(Region, 100);
                
                Address.checkColumnSize(Locale, 10);
                                            
                openConnection();
                prepareStatement("INSERT INTO address(Recipient,Address1,Address2,City,StateProvince,ZipPostalCode,Country,Region,AddressStatus,Locale) VALUES (?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, Recipient);
                preparedStatement.setString(2, Address1);
                preparedStatement.setString(3, Address2);
                preparedStatement.setString(4, City);
                preparedStatement.setString(5, StateProvince);
                preparedStatement.setString(6, ZipPostalCode);
                preparedStatement.setString(7, Country);
                preparedStatement.setString(8, Region);
                preparedStatement.setInt(9, AddressStatus);
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
        
        
        @Override
        public Address update(Address obj)
        {
           try
            {   
                
                Address.checkColumnSize(obj.getRecipient(), 100);
                Address.checkColumnSize(obj.getAddress1(), 255);
                Address.checkColumnSize(obj.getAddress2(), 255);
                Address.checkColumnSize(obj.getCity(), 100);
                Address.checkColumnSize(obj.getStateProvince(), 100);
                Address.checkColumnSize(obj.getZipPostalCode(), 10);
                Address.checkColumnSize(obj.getCountry(), 100);
                Address.checkColumnSize(obj.getRegion(), 100);
                
                Address.checkColumnSize(obj.getLocale(), 10);
                                  
                openConnection();                           
                prepareStatement("UPDATE address SET Recipient=?,Address1=?,Address2=?,City=?,StateProvince=?,ZipPostalCode=?,Country=?,Region=?,AddressStatus=?,Locale=? WHERE AddressId=?;");                    
                preparedStatement.setString(1, obj.getRecipient());
                preparedStatement.setString(2, obj.getAddress1());
                preparedStatement.setString(3, obj.getAddress2());
                preparedStatement.setString(4, obj.getCity());
                preparedStatement.setString(5, obj.getStateProvince());
                preparedStatement.setString(6, obj.getZipPostalCode());
                preparedStatement.setString(7, obj.getCountry());
                preparedStatement.setString(8, obj.getRegion());
                preparedStatement.setInt(9, obj.getAddressStatus());
                preparedStatement.setString(10, obj.getLocale());
                preparedStatement.setInt(11, obj.getAddressId());
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
            return obj;
        }
        
        public static void update(Integer AddressId,String Recipient,String Address1,String Address2,String City,String StateProvince,String ZipPostalCode,String Country,String Region,Integer AddressStatus,String Locale)
        {  
            try
            {   
                
                Address.checkColumnSize(Recipient, 100);
                Address.checkColumnSize(Address1, 255);
                Address.checkColumnSize(Address2, 255);
                Address.checkColumnSize(City, 100);
                Address.checkColumnSize(StateProvince, 100);
                Address.checkColumnSize(ZipPostalCode, 10);
                Address.checkColumnSize(Country, 100);
                Address.checkColumnSize(Region, 100);
                
                Address.checkColumnSize(Locale, 10);
                                  
                openConnection();                           
                prepareStatement("UPDATE address SET Recipient=?,Address1=?,Address2=?,City=?,StateProvince=?,ZipPostalCode=?,Country=?,Region=?,AddressStatus=?,Locale=? WHERE AddressId=?;");                    
                preparedStatement.setString(1, Recipient);
                preparedStatement.setString(2, Address1);
                preparedStatement.setString(3, Address2);
                preparedStatement.setString(4, City);
                preparedStatement.setString(5, StateProvince);
                preparedStatement.setString(6, ZipPostalCode);
                preparedStatement.setString(7, Country);
                preparedStatement.setString(8, Region);
                preparedStatement.setInt(9, AddressStatus);
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
        
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("address");
        }
        
        
        @Override
        public Address getRelatedInfo(Address address)
        {
              
            
            return address;
        }
        
        
        @Override
        public Address getRelatedObjects(Address address)
        {
            address.setAffiliateList(new AffiliateDaoImpl().findByColumn("AddressId", address.getAddressId().toString(),null,null));
address.setItemLocationList(new ItemLocationDaoImpl().findByColumn("AddressId", address.getAddressId().toString(),null,null));
address.setUserList(new UserDaoImpl().findByColumn("AddressId", address.getAddressId().toString(),null,null));
             
            return address;
        }
        
        
        
        @Override
        public void remove(Address obj)
        {            
            try
            {
                updateQuery("DELETE FROM address WHERE AddressId=" + obj.getAddressId() + ";");            
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
        
        @Override
        public void remove(Integer id)
        {            
            try
            {
                updateQuery("DELETE FROM address WHERE AddressId=" + id.intValue() + ";");            
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

        @Override
        public void removeAll()
        {
            try
            {
                updateQuery("DELETE FROM address;");
            }
            catch (Exception ex)
            {
                System.out.println("Address's deleteAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM address WHERE " + Address.checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("Address's deleteByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
                    
        public Address getRelatedAffiliateList(Address address)
        {           
            address.setAffiliateList(new AffiliateDaoImpl().findByColumn("AddressId", address.getAddressId().toString(),null,null));
            return address;
        }        
                    
        public Address getRelatedItemLocationList(Address address)
        {           
            address.setItemLocationList(new ItemLocationDaoImpl().findByColumn("AddressId", address.getAddressId().toString(),null,null));
            return address;
        }        
                    
        public Address getRelatedUserList(Address address)
        {           
            address.setUserList(new UserDaoImpl().findByColumn("AddressId", address.getAddressId().toString(),null,null));
            return address;
        }        
        
                             
    }

