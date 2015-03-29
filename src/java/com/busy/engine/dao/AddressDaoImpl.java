package com.busy.engine.dao;

import com.busy.engine.data.BasicConnection;
import com.busy.engine.entity.*;
import com.busy.engine.util.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map.Entry;
import java.lang.reflect.InvocationTargetException;

public class AddressDaoImpl extends BasicConnection implements Serializable, AddressDao
{

    private static final long serialVersionUID = 1L;
    private boolean cachingEnabled;

    public AddressDaoImpl()
    {
        cachingEnabled = false;
    }

    public AddressDaoImpl(boolean enableCache)
    {
        cachingEnabled = enableCache;
    }

    private static class AddressCache
    {

        public static final ConcurrentLruCache<Integer, Address> addressCache = buildCache(findAll());
    }

    private void checkCacheState()
    {
        if (getCache().size() == 0)
        {
            System.out.println("Found the cache empty, rebuilding...");
            for (Address i : findAll())
            {
                getCache().put(i.getAddressId(), i);
            }
        }
    }

    public static ConcurrentLruCache<Integer, Address> getCache()
    {
        return AddressCache.addressCache;
    }

    protected Object readResolve()
    {
        return getCache();
    }

    public static ConcurrentLruCache<Integer, Address> buildCache(ArrayList<Address> addressList)
    {
        ConcurrentLruCache<Integer, Address> cache = new ConcurrentLruCache<Integer, Address>(addressList.size() + 1000);
        for (Address i : addressList)
        {
            cache.put(i.getAddressId(), i);
        }
        return cache;
    }

    private static ArrayList<Address> findAll()
    {
        ArrayList<Address> address = new ArrayList<>();
        try
        {
            getAllRecordsByTableName("address");
            while (rs.next())
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
    public Address find(Integer id)
    {
        return findByColumn("AddressId", id.toString(), null, null).get(0);
    }

    @Override
    public Address findWithInfo(Integer id)
    {
        Address address = findByColumn("AddressId", id.toString(), null, null).get(0);

        return address;
    }

    @Override
    public ArrayList<Address> findAll(Integer limit, Integer offset)
    {
        ArrayList<Address> addressList = new ArrayList<Address>();
        boolean cacheNotUsed = false;

        //check cache first
        if (cachingEnabled)
        {
            System.out.println("Find all operation for Address, getting objects from cache...");
            checkCacheState();

            if (limit == null && offset == null)
            {
                addressList = new ArrayList<Address>(getCache().getValues());
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
                getRecordsByTableNameWithLimitOrOffset("address", limit, offset);
                while (rs.next())
                {
                    addressList.add(Address.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Address object's findAll method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        return addressList;

    }

    @Override
    public ArrayList<Address> findAllWithInfo(Integer limit, Integer offset)
    {
        ArrayList<Address> addressList = new ArrayList<Address>();
        boolean cacheNotUsed = false;

        //check cache first
        if (cachingEnabled)
        {
            checkCacheState();

            System.out.println("Find all with info operation for Address, getting objects from cache...");

            if (limit == null && offset == null)
            {
                addressList = new ArrayList<Address>(getCache().getValues());
            }
            else
            {
                cacheNotUsed = true;
            }

        }

        if (!cachingEnabled || cacheNotUsed)
        {
            addressList = new ArrayList<Address>();
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
        }
        return addressList;
    }

    @Override
    public ArrayList<Address> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
    {
        ArrayList<Address> addressList = new ArrayList<>();
        boolean cacheNotUsed = false;

        if (cachingEnabled)
        {
            if (limit == null && offset == null)
            {

                System.out.println("Find by column for Address(" + columnName + "=" + columnValue + "), getting objects from cache...");
                for (Entry e : getCache().getEntries())
                {
                    try
                    {
                        Address i = (Address) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            addressList.add(i);
                        }
                    }
                    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                    {
                        ex.printStackTrace();
                        addressList = null;
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
                getRecordsByColumnWithLimitOrOffset("address", Address.checkColumnName(columnName), columnValue, Address.isColumnNumeric(columnName), limit, offset);
                while (rs.next())
                {
                    addressList.add(Address.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Address's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        return addressList;
    }

    @Override
    public int add(Address obj)
    {
        boolean success = false;
        int id = 0;
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

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from address;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }

            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("Address's add method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        if (cachingEnabled && success)
        {
            obj.setAddressId(id);
            getCache().put(id, obj); //synchronizing between local cache and database
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

            if (cachingEnabled)
            {
                getCache().put(obj.getAddressId(), obj);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Address's update error: " + ex.getMessage());
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
            count = getAllRecordsCountByTableName("address");
        }
        return count;
    }

    @Override
    public void getRelatedInfo(Address address)
    {

    }

    @Override
    public void getRelatedObjects(Address address)
    {
        address.setAffiliateList(new AffiliateDaoImpl().findByColumn("AddressId", address.getAddressId().toString(), null, null));
        address.setItemLocationList(new ItemLocationDaoImpl().findByColumn("AddressId", address.getAddressId().toString(), null, null));
        address.setUserList(new UserDaoImpl().findByColumn("AddressId", address.getAddressId().toString(), null, null));

    }

    @Override
    public boolean remove(Address obj)
    {
        boolean success = false;
        try
        {
            updateQuery("DELETE FROM address WHERE AddressId=" + obj.getAddressId() + ";");
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("Address's remove error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        if (cachingEnabled && success)
        {
            getCache().remove(obj.getAddressId());
        }

        return success;
    }

    @Override
    public boolean removeById(Integer id)
    {
        boolean success = false;
        try
        {
            updateQuery("DELETE FROM address WHERE AddressId=" + id + ";");
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
            updateQuery("DELETE FROM address;");
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("Address's removeAll() method error: " + ex.getMessage());
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
            updateQuery("DELETE FROM address WHERE " + Address.checkColumnName(columnName) + "=" + columnValue + ";");
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("Address's removeByColumn method error: " + ex.getMessage());
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
                    Address i = (Address) e.getValue();
                    if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                    {
                        keys.add(i.getAddressId());
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

    public void getRelatedAffiliateList(Address address)
    {
        address.setAffiliateList(new AffiliateDaoImpl().findByColumn("AddressId", address.getAddressId().toString(), null, null));
    }

    public void getRelatedItemLocationList(Address address)
    {
        address.setItemLocationList(new ItemLocationDaoImpl().findByColumn("AddressId", address.getAddressId().toString(), null, null));
    }

    public void getRelatedUserList(Address address)
    {
        address.setUserList(new UserDaoImpl().findByColumn("AddressId", address.getAddressId().toString(), null, null));
    }

}
