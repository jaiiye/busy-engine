package com.busy.engine.dao;

import com.busy.engine.entity.Customer;
import com.busy.engine.entity.Contact;
import com.busy.engine.entity.Address;
import com.busy.engine.entity.User;
import com.busy.engine.data.BasicConnection;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.SQLException;

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
            System.out.println("Customer's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return customer;
    }

    @Override
    public int add(Customer obj)
    {
        int id = 0;
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

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from customer;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Customer's add method error: " + ex.getMessage());
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
            System.out.println("Customer's update error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return obj;
    }

    @Override
    public int getAllCount()
    {
        return getAllRecordsCountByTableName("customer");
    }

    @Override
    public void getRelatedInfo(Customer customer)
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

    }

    @Override
    public void getRelatedObjects(Customer customer)
    {
        customer.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("CustomerId", customer.getCustomerId().toString(), null, null));
    }

    @Override
    public boolean remove(Customer obj)
    {
        boolean success = false;
        try
        {
            updateQuery("DELETE FROM customer WHERE CustomerId=" + obj.getCustomerId() + ";");
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("Customer's remove error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return success;
    }

    @Override
    public boolean removeById(Integer id)
    {
        boolean success = false;
        try
        {
            updateQuery("DELETE FROM customer WHERE CustomerId=" + id + ";");
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("removeById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return success;
    }

    @Override
    public boolean removeAll()
    {
        boolean success = false;
        try
        {
            updateQuery("DELETE FROM customer;");
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("Customer's removeAll() method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return success;
    }

    @Override
    public boolean removeByColumn(String columnName, String columnValue)
    {
        boolean success = false;
        try
        {
            updateQuery("DELETE FROM customer WHERE " + Customer.checkColumnName(columnName) + "=" + columnValue + ";");
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("Customer's removeByColumn method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return success;
    }

    public void getRelatedCustomerOrderList(Customer customer)
    {
        customer.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("CustomerId", customer.getCustomerId().toString(), null, null));
    }

}
