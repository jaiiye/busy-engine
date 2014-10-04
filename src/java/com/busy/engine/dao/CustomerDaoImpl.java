package com.busy.engine.dao;

import com.busy.engine.data.BasicConnection;
import com.busy.engine.entity.*;
import com.busy.engine.dao.*;
import com.busy.engine.util.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map.Entry;
import java.lang.reflect.InvocationTargetException;

public class CustomerDaoImpl extends BasicConnection implements Serializable, CustomerDao
{

    private static final long serialVersionUID = 1L;
    private boolean cachingEnabled;

    public CustomerDaoImpl()
    {
        cachingEnabled = false;
    }

    public CustomerDaoImpl(boolean enableCache)
    {
        cachingEnabled = enableCache;
    }

    private static class CustomerCache
    {

        public static final ConcurrentLruCache<Integer, Customer> customerCache = buildCache(findAll());
    }

    private void checkCacheState()
    {
        if (getCache().size() == 0)
        {
            System.out.println("Found the cache empty, rebuilding...");
            for (Customer i : findAll())
            {
                getCache().put(i.getCustomerId(), i);
            }
        }
    }

    public static ConcurrentLruCache<Integer, Customer> getCache()
    {
        return CustomerCache.customerCache;
    }

    protected Object readResolve()
    {
        return getCache();
    }

    public static ConcurrentLruCache<Integer, Customer> buildCache(ArrayList<Customer> customerList)
    {
        ConcurrentLruCache<Integer, Customer> cache = new ConcurrentLruCache<Integer, Customer>(customerList.size() + 1000);
        for (Customer i : customerList)
        {
            cache.put(i.getCustomerId(), i);
        }
        return cache;
    }

    private static ArrayList<Customer> findAll()
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
    public Customer find(Integer id)
    {
        return findByColumn("CustomerId", id.toString(), null, null).get(0);
    }

    @Override
    public ArrayList<Customer> findAll(Integer limit, Integer offset)
    {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        boolean cacheNotUsed = false;

        //check cache first
        if (cachingEnabled)
        {
            System.out.println("Find all operation for Customer, getting objects from cache...");
            checkCacheState();

            if (limit == null && offset == null)
            {
                customerList = new ArrayList<Customer>(getCache().getValues());
            }
            else
            {
                cacheNotUsed = true;
            }
        }

        if (!cachingEnabled || cacheNotUsed)
        {
            try
            {
                getRecordsByTableNameWithLimitOrOffset("customer", limit, offset);
                while (rs.next())
                {
                    customerList.add(Customer.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Customer object's findAll method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        return customerList;

    }

    @Override
    public ArrayList<Customer> findAllWithInfo(Integer limit, Integer offset)
    {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        boolean cacheNotUsed = false;

        //check cache first
        if (cachingEnabled)
        {
            checkCacheState();

            System.out.println("Find all with info operation for Customer, getting objects from cache...");

            if (limit == null && offset == null)
            {
                customerList = new ArrayList<Customer>(getCache().getValues());
            }
            else
            {
                cacheNotUsed = true;
            }

            try
            {
                for (Entry e : getCache().getEntries())
                {
                    Customer customer = (Customer) e.getValue();

                    getRecordById("Contact", customer.getContactId().toString());
                    customer.setContact(Contact.process(rs));

                    getRecordById("User", customer.getUserId().toString());
                    customer.setUser(User.process(rs));

                    getRecordById("BillingAddress", customer.getBillingAddressId().toString());
                    customer.setBillingAddress(Address.process(rs));

                    getRecordById("ShippingAddress", customer.getShippingAddressId().toString());
                    customer.setShippingAddress(Address.process(rs));

                }
            }
            catch (SQLException ex)
            {
                System.out.println("Object Customer method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

        }

        if (!cachingEnabled || cacheNotUsed)
        {
            customerList = new ArrayList<Customer>();
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

                    getRecordById("BillingAddress", customer.getBillingAddressId().toString());
                    customer.setBillingAddress(Address.process(rs));

                    getRecordById("ShippingAddress", customer.getShippingAddressId().toString());
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
        }
        return customerList;
    }

    @Override
    public ArrayList<Customer> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
    {
        ArrayList<Customer> customerList = new ArrayList<>();
        boolean cacheNotUsed = false;

        if (cachingEnabled)
        {
            if (limit == null && offset == null)
            {

                System.out.println("Find by column for Customer(" + columnName + "=" + columnValue + "), getting objects from cache...");
                for (Entry e : getCache().getEntries())
                {
                    try
                    {
                        Customer i = (Customer) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            customerList.add(i);
                        }
                    }
                    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                    {
                        ex.printStackTrace();
                        customerList = null;
                    }
                }
            }
            else
            {
                cacheNotUsed = true;
            }
        }

        if (!cachingEnabled || cacheNotUsed)
        {
            try
            {
                getRecordsByColumnWithLimitOrOffset("customer", Customer.checkColumnName(columnName), columnValue, Customer.isColumnNumeric(columnName), limit, offset);
                while (rs.next())
                {
                    customerList.add(Customer.process(rs));
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
        }
        return customerList;
    }

    @Override
    public int add(Customer obj)
    {
        boolean success = false;
        int id = 0;
        try
        {

            openConnection();
            prepareStatement("INSERT INTO customer(CustomerId,ContactId,UserId,BillingAddressId,ShippingAddressId,CustomerStatus,) VALUES (?,?,?,?,?);");
            preparedStatement.setInt(0, obj.getCustomerId());
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

            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("Customer's add method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        if (cachingEnabled && success)
        {
            obj.setCustomerId(id);
            getCache().put(id, obj); //synchronizing between local cache and database
        }

        return id;
    }

    @Override
    public Customer update(Customer obj)
    {
        try
        {

            openConnection();
            prepareStatement("UPDATE customer SET com.busy.util.DatabaseColumn@362ea91d=?,com.busy.util.DatabaseColumn@34201002=?,com.busy.util.DatabaseColumn@4babe02d=?,com.busy.util.DatabaseColumn@410282c4=?,com.busy.util.DatabaseColumn@2a7bdb8=? WHERE CustomerId=?;");
            preparedStatement.setInt(0, obj.getCustomerId());
            preparedStatement.setInt(1, obj.getContactId());
            preparedStatement.setInt(2, obj.getUserId());
            preparedStatement.setInt(3, obj.getBillingAddressId());
            preparedStatement.setInt(4, obj.getShippingAddressId());
            preparedStatement.setInt(5, obj.getCustomerStatus());
            preparedStatement.setInt(6, obj.getCustomerId());
            preparedStatement.executeUpdate();

            if (cachingEnabled)
            {
                getCache().put(obj.getCustomerId(), obj);
            }
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
        int count = 0;
        if (cachingEnabled)
        {
            count = getCache().size();
        }
        else
        {
            count = getAllRecordsCountByTableName("customer");
        }
        return count;
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

            getRecordById("BillingAddress", customer.getBillingAddressId().toString());
            customer.setBillingAddress(Address.process(rs));

            getRecordById("ShippingAddress", customer.getShippingAddressId().toString());
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

        if (cachingEnabled && success)
        {
            getCache().remove(obj.getCustomerId());
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

        if (cachingEnabled && success)
        {
            getCache().remove(id);
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

        if (cachingEnabled && success)
        {
            getCache().clear();
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

        if (cachingEnabled && success)
        {
            ArrayList<Integer> keys = new ArrayList<Integer>();

            for (Entry e : getCache().getEntries())
            {
                try
                {
                    Customer i = (Customer) e.getValue();
                    if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                    {
                        keys.add(i.getCustomerId());
                    }
                }
                catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                {
                    ex.printStackTrace();
                }
            }

            for (int id : keys)
            {
                getCache().remove(id);
            }
        }

        return success;
    }

    public boolean isCachingEnabled()
    {
        return cachingEnabled;
    }

    public void setCachingEnabled(boolean cachingEnabled)
    {
        this.cachingEnabled = cachingEnabled;
    }

    public void getRelatedCustomerOrderList(Customer customer)
    {
        customer.setCustomerOrderList(new CustomerOrderDaoImpl().findByColumn("CustomerId", customer.getCustomerId().toString(), null, null));
    }

}
