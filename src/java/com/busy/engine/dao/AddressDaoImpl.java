package com.busy.engine.dao;

import com.busy.engine.entity.Address;
import com.busy.engine.data.BasicConnection;
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
            while (rs.next())
            {
                address.add(Address.process(rs));
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
        return address;
    }

    @Override
    public int add(Address obj)
    {
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
        }
        catch (Exception ex)
        {
            System.out.println("Address's add method error: " + ex.getMessage());
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
        return getAllRecordsCountByTableName("address");
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
        return success;
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
