package com.busy.dao;

import com.transitionsoft.BasicConnection;
import com.busy.entity.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CustomerDAO extends BasicConnection implements Serializable
{

    private static final long serialVersionUID = 1L;

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(Customer.PROP_CUSTOMER_ID) || column.equals(Customer.PROP_CONTACT_ID) || column.equals(Customer.PROP_USER_ID) || column.equals(Customer.PROP_BILLING_ADDRESS_ID) || column.equals(Customer.PROP_SHIPPING_ADDRESS_ID) || column.equals(Customer.PROP_CUSTOMER_STATUS))
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
        if (column.equals(Customer.PROP_CUSTOMER_ID) || column.equals(Customer.PROP_CONTACT_ID) || column.equals(Customer.PROP_USER_ID) || column.equals(Customer.PROP_BILLING_ADDRESS_ID) || column.equals(Customer.PROP_SHIPPING_ADDRESS_ID) || column.equals(Customer.PROP_CUSTOMER_STATUS))
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
        return new Customer(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
    }

    public static int addCustomer(Integer ContactId, Integer UserId, Integer BillingAddressId, Integer ShippingAddressId, Integer CustomerStatus)
    {
        int id = 0;
        try
        {

            openConnection();
            prepareStatement("INSERT INTO customer(ContactId,UserId,BillingAddressId,ShippingAddressId,CustomerStatus) VALUES (?,?,?,?,?);");
            preparedStatement.setInt(1, ContactId);
            preparedStatement.setInt(2, UserId);
            preparedStatement.setInt(3, BillingAddressId);
            preparedStatement.setInt(4, ShippingAddressId);
            preparedStatement.setInt(5, CustomerStatus);

            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from customer;");
            while (rs.next())
            {
                id = rs.getInt(1);
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
            while (rs.next())
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

    public static ArrayList<Customer> getAllCustomerWithRelatedInfo()
    {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        try
        {
            getAllRecordsByTableName("customer");
            while (rs.next())
            {
                customerList.add(processCustomer(rs));
            }

            for (Customer customer : customerList)
            {

                getRecordById("Contact", customer.getContactId().toString());
                customer.setContact(ContactDAO.processContact(rs));

                getRecordById("User", customer.getUserId().toString());
                customer.setUser(UserDAO.processUser(rs));

                getRecordById("Address", customer.getBillingAddressId().toString());
                customer.setBillingAddress(AddressDAO.processAddress(rs));

                getRecordById("Address", customer.getShippingAddressId().toString());
                customer.setShippingAddress(AddressDAO.processAddress(rs));

            }

        }
        catch (SQLException ex)
        {
            System.out.println("getAllCustomerWithRelatedInfo error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return customerList;
    }

    public static Customer getRelatedInfo(Customer customer)
    {

        try
        {

            getRecordById("Contact", customer.getContactId().toString());
            customer.setContact(ContactDAO.processContact(rs));

            getRecordById("User", customer.getUserId().toString());
            customer.setUser(UserDAO.processUser(rs));

            getRecordById("Address", customer.getBillingAddressId().toString());
            customer.setBillingAddress(AddressDAO.processAddress(rs));

            getRecordById("Address", customer.getShippingAddressId().toString());
            customer.setShippingAddress(AddressDAO.processAddress(rs));

        }
        catch (SQLException ex)
        {
            System.out.println("getRelatedInfo error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        return customer;
    }

    public static Customer getAllRelatedObjects(Customer customer)
    {
        customer.setCustomerOrderList(CustomerOrderDAO.getAllCustomerOrderByColumn("CustomerId", customer.getCustomerId().toString()));

        return customer;
    }

    public static Customer getRelatedCustomerOrderList(Customer customer)
    {
        customer.setCustomerOrderList(CustomerOrderDAO.getAllCustomerOrderByColumn("CustomerId", customer.getCustomerId().toString()));
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
            while (rs.next())
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
            while (rs.next())
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

    public static void updateCustomer(Integer CustomerId, Integer ContactId, Integer UserId, Integer BillingAddressId, Integer ShippingAddressId, Integer CustomerStatus)
    {
        try
        {

            openConnection();
            prepareStatement("UPDATE customer SET ContactId=?,UserId=?,BillingAddressId=?,ShippingAddressId=?,CustomerStatus=? WHERE CustomerId=?;");
            preparedStatement.setInt(1, ContactId);
            preparedStatement.setInt(2, UserId);
            preparedStatement.setInt(3, BillingAddressId);
            preparedStatement.setInt(4, ShippingAddressId);
            preparedStatement.setInt(5, CustomerStatus);
            preparedStatement.setInt(6, CustomerId);
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