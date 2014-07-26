package com.busy.engine.dao.impl;

import com.transitionsoft.BasicConnection;
import com.busy.engine.domain.*;
import com.busy.engine.dao.base.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;

public class CustomerDaoImpl extends BasicConnection implements Serializable, CustomerDao
{

    private static final long serialVersionUID = 1L;

    @Override
    public Customer find(Integer id)
    {
        return findByColumn("CustomerId", id.toString(), null, null).get(0);
    }

    @Override
    public ArrayList<Customer> findAll(Integer limit, Integer offset)
    {
        ArrayList<Customer> customer = new ArrayList<>();
        try
        {
            getAllRecordsByTableName("customer");
            while (rs.next())
            {
                customer.add(Customer.process(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Customer object's findAll() method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return customer;

    }

    @Override
    public ArrayList<Customer> findAllWithInfo(Integer limit, Integer offset)
    {
        ArrayList<Customer> customerList = new ArrayList<>();
        try
        {
            getRecordsByTableNameWithLimitOrOffset("customer", limit, offset);
            while (rs.next())
            {
                customerList.add(Customer.process(rs));
            }

            for (Customer customer : customerList)
            {

                getRecordById("Contact", customer.getContactId().toString());
                customer.setContact(Contact.process(rs));

                getRecordById("User", customer.getUserId().toString());
                customer.setUser(User.process(rs));

                getRecordById("Address", customer.getBillingAddressId().toString());
                customer.setBillingAddress(Address.process(rs));

                getRecordById("Address", customer.getShippingAddressId().toString());
                customer.setShippingAddress(Address.process(rs));

            }

        }
        catch (SQLException ex)
        {
            System.out.println("Object Customer method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return customerList;
    }

    @Override
    public ArrayList<Customer> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
    {
        ArrayList<Customer> customer = new ArrayList<>();
        try
        {
            getRecordsByColumnWithLimitOrOffset("customer", Customer.checkColumnName(columnName), columnValue, Customer.isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                customer.add(Customer.process(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Object Customer's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return customer;
    }

    @Override
    public void add(Customer obj)
    {
        try
        {

            openConnection();
            prepareStatement("INSERT INTO customer(ContactId,UserId,BillingAddressId,ShippingAddressId,CustomerStatus) VALUES (?,?,?,?,?);");
            preparedStatement.setInt(1, obj.getContactId());
            preparedStatement.setInt(2, obj.getUserId());
            preparedStatement.setInt(3, obj.getBillingAddressId());
            preparedStatement.setInt(4, obj.getShippingAddressId());
            preparedStatement.setInt(5, obj.getCustomerStatus());

            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("Customer's add method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static int add(Integer ContactId, Integer UserId, Integer BillingAddressId, Integer ShippingAddressId, Integer CustomerStatus)
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

    @Override
    public Customer update(Customer obj)
    {
        try
        {

            openConnection();
            prepareStatement("UPDATE customer SET ContactId=?,UserId=?,BillingAddressId=?,ShippingAddressId=?,CustomerStatus=? WHERE CustomerId=?;");
            preparedStatement.setInt(1, obj.getContactId());
            preparedStatement.setInt(2, obj.getUserId());
            preparedStatement.setInt(3, obj.getBillingAddressId());
            preparedStatement.setInt(4, obj.getShippingAddressId());
            preparedStatement.setInt(5, obj.getCustomerStatus());
            preparedStatement.setInt(6, obj.getCustomerId());
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
        return obj;
    }

    public static void update(Integer CustomerId, Integer ContactId, Integer UserId, Integer BillingAddressId, Integer ShippingAddressId, Integer CustomerStatus)
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

    @Override
    public int getAllCount()
    {
        return getAllRecordsCountByTableName("customer");
    }

    @Override
    public Customer getRelatedInfo(Customer customer)
    {

        try
        {

            getRecordById("Contact", customer.getContactId().toString());
            customer.setContact(Contact.process(rs));

            getRecordById("User", customer.getUserId().toString());
            customer.setUser(User.process(rs));

            getRecordById("Address", customer.getBillingAddressId().toString());
            customer.setBillingAddress(Address.process(rs));

            getRecordById("Address", customer.getShippingAddressId().toString());
            customer.setShippingAddress(Address.process(rs));

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

    @Override
    public Customer getRelatedObjects(Customer customer)
    {
        customer.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("CustomerId", customer.getCustomerId().toString(), null, null));

        return customer;
    }

    @Override
    public void remove(Customer obj)
    {
        try
        {
            updateQuery("DELETE FROM customer WHERE CustomerId=" + obj.getCustomerId() + ";");
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

    @Override
    public void remove(Integer id)
    {
        try
        {
            updateQuery("DELETE FROM customer WHERE CustomerId=" + id.intValue() + ";");
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

    @Override
    public void removeAll()
    {
        try
        {
            updateQuery("DELETE FROM customer;");
        }
        catch (Exception ex)
        {
            System.out.println("Customer's deleteAll() method error: " + ex.getMessage());
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
            updateQuery("DELETE FROM customer WHERE " + Customer.checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("Customer's deleteByColumn method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public Customer getRelatedCustomerOrderList(Customer customer)
    {
        customer.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("CustomerId", customer.getCustomerId().toString(), null, null));
        return customer;
    }

}
