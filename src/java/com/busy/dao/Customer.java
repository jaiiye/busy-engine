


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class Customer extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_CUSTOMERID = "CustomerId";
        public static final String PROP_CONTACTID = "ContactId";
        public static final String PROP_ADDRESSID = "AddressId";
        
        
        private Integer customerId;
        private Integer contactId;
        private Integer addressId;
        
        
        public Customer()
        {
            this.customerId = 0; 
       this.contactId = 0; 
       this.addressId = 0; 
        }
        
        public Customer(Integer CustomerId, Integer ContactId, Integer AddressId)
        {
            this.customerId = CustomerId;
       this.contactId = ContactId;
       this.addressId = AddressId;
        } 
        
        
            public Integer getCustomerId()
            {
                return this.customerId;
            }
            
            public void setCustomerId(Integer CustomerId)
            {
                this.customerId = CustomerId;
            }
        
            public Integer getContactId()
            {
                return this.contactId;
            }
            
            public void setContactId(Integer ContactId)
            {
                this.contactId = ContactId;
            }
        
            public Integer getAddressId()
            {
                return this.addressId;
            }
            
            public void setAddressId(Integer AddressId)
            {
                this.addressId = AddressId;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Customer.PROP_CUSTOMERID) || column.equals(Customer.PROP_CONTACTID) || column.equals(Customer.PROP_ADDRESSID) )
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
            if (column.equals(Customer.PROP_CUSTOMERID) || column.equals(Customer.PROP_CONTACTID) || column.equals(Customer.PROP_ADDRESSID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Customer processCustomer(ResultSet rs) throws SQLException
        {        
            return new Customer(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
        
        public static int addCustomer(Integer ContactId, Integer AddressId)
        {   
            int id = 0;
            try
            {
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO customer(ContactId,AddressId) VALUES (?,?);");                    
                preparedStatement.setInt(1, ContactId);
                preparedStatement.setInt(2, AddressId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from customer;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addCustomer error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllCustomerCount()
        {
            return getAllRecordsCountByTableName("customer");        
        }
        
        public static ArrayList<Customer> getAllCustomer()
        {
            ArrayList<Customer> customer = new ArrayList<Customer>();
            try
            {
                getAllRecordsByTableName("customer");
                while(rs.next())
                {
                    customer.add(processCustomer(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllCustomer error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customer;
        }
                
        public static ArrayList<Customer> getCustomerPaged(int limit, int offset)
        {
            ArrayList<Customer> customer = new ArrayList<Customer>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("customer", limit, offset);
                while (rs.next())
                {
                    customer.add(processCustomer(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getCustomerPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customer;
        } 
        
        public static ArrayList<Customer> getAllCustomerByColumn(String columnName, String columnValue)
        {
            ArrayList<Customer> customer = new ArrayList<Customer>();
            try
            {
                getAllRecordsByColumn("customer", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    customer.add(processCustomer(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllCustomerByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customer;
        }
        
        public static Customer getCustomerByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            Customer customer = new Customer();
            try
            {
                getRecordsByColumnWithLimitAndOffset("customer", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   customer = processCustomer(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getCustomerByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return customer;
        }                
                
        public static void updateCustomer(Integer CustomerId,Integer ContactId,Integer AddressId)
        {  
            try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE customer SET ContactId=?,AddressId=? WHERE CustomerId=?;");                    
                preparedStatement.setInt(1, ContactId);
                preparedStatement.setInt(2, AddressId);
                preparedStatement.setInt(3, CustomerId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateCustomer error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllCustomer()
        {
            try
            {
                updateQuery("DELETE FROM customer;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllCustomer error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteCustomerById(String id)
        {
            try
            {
                updateQuery("DELETE FROM customer WHERE CustomerId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteCustomerById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteCustomerByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM customer WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteCustomerByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

