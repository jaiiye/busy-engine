package com.busy.dao;

import com.transitionsoft.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Address extends BasicConnection implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_ADDRESSID = "AddressId";
    public static final String PROP_ADDRESS1 = "Address1";
    public static final String PROP_ADDRESS2 = "Address2";
    public static final String PROP_CITY = "City";
    public static final String PROP_STATE = "State";
    public static final String PROP_ZIPCODE = "Zipcode";
    public static final String PROP_COUNTRY = "Country";
    public static final String PROP_REGION = "Region";
    public static final String PROP_USERID = "UserId";

    private Integer addressId;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipcode;
    private String country;
    private String region;
    private Integer userId;

    public Address()
    {
        this.addressId = 0;
        this.address1 = "";
        this.address2 = "";
        this.city = "";
        this.state = "";
        this.zipcode = "";
        this.country = "";
        this.region = "";
        this.userId = 0;
    }

    public Address(Integer AddressId, String Address1, String Address2, String City, String State, String Zipcode, String Country, String Region, Integer UserId)
    {
        this.addressId = AddressId;
        this.address1 = Address1;
        this.address2 = Address2;
        this.city = City;
        this.state = State;
        this.zipcode = Zipcode;
        this.country = Country;
        this.region = Region;
        this.userId = UserId;
    }

    public Integer getAddressId()
    {
        return this.addressId;
    }

    public void setAddressId(Integer AddressId)
    {
        this.addressId = AddressId;
    }

    public String getAddress1()
    {
        return this.address1;
    }

    public void setAddress1(String Address1)
    {
        this.address1 = Address1;
    }

    public String getAddress2()
    {
        return this.address2;
    }

    public void setAddress2(String Address2)
    {
        this.address2 = Address2;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCity(String City)
    {
        this.city = City;
    }

    public String getState()
    {
        return this.state;
    }

    public void setState(String State)
    {
        this.state = State;
    }

    public String getZipcode()
    {
        return this.zipcode;
    }

    public void setZipcode(String Zipcode)
    {
        this.zipcode = Zipcode;
    }

    public String getCountry()
    {
        return this.country;
    }

    public void setCountry(String Country)
    {
        this.country = Country;
    }

    public String getRegion()
    {
        return this.region;
    }

    public void setRegion(String Region)
    {
        this.region = Region;
    }

    public Integer getUserId()
    {
        return this.userId;
    }

    public void setUserId(Integer UserId)
    {
        this.userId = UserId;
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(Address.PROP_ADDRESSID) || column.equals(Address.PROP_ADDRESS1) || column.equals(Address.PROP_ADDRESS2) || column.equals(Address.PROP_CITY) || column.equals(Address.PROP_STATE) || column.equals(Address.PROP_ZIPCODE) || column.equals(Address.PROP_COUNTRY) || column.equals(Address.PROP_REGION) || column.equals(Address.PROP_USERID))
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
        if (column.equals(Address.PROP_ADDRESSID) || column.equals(Address.PROP_USERID))
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
        return new Address(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
    }

    public static int addAddress(String Address1, String Address2, String City, String State, String Zipcode, String Country, String Region, Integer UserId)
    {
        int id = 0;
        try
        {
            checkColumnSize(Address1, 255);
            checkColumnSize(Address2, 255);
            checkColumnSize(City, 100);
            checkColumnSize(State, 100);
            checkColumnSize(Zipcode, 10);
            checkColumnSize(Country, 150);
            checkColumnSize(Region, 100);

            openConnection();
            prepareStatement("INSERT INTO address(Address1,Address2,City,State,Zipcode,Country,Region,UserId) VALUES (?,?,?,?,?,?,?,?);");
            preparedStatement.setString(1, Address1);
            preparedStatement.setString(2, Address2);
            preparedStatement.setString(3, City);
            preparedStatement.setString(4, State);
            preparedStatement.setString(5, Zipcode);
            preparedStatement.setString(6, Country);
            preparedStatement.setString(7, Region);
            preparedStatement.setInt(8, UserId);
            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from address;");
            while (rs.next())
            {
                id = rs.getInt(1);
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
            while (rs.next())
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
            while (rs.next())
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
            while (rs.next())
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

    public static void updateAddress(Integer AddressId, String Address1, String Address2, String City, String State, String Zipcode, String Country, String Region, Integer UserId)
    {
        try
        {
            checkColumnSize(Address1, 255);
            checkColumnSize(Address2, 255);
            checkColumnSize(City, 100);
            checkColumnSize(State, 100);
            checkColumnSize(Zipcode, 10);
            checkColumnSize(Country, 150);
            checkColumnSize(Region, 100);

            openConnection();
            prepareStatement("UPDATE address SET Address1=?,Address2=?,City=?,State=?,Zipcode=?,Country=?,Region=?,UserId=? WHERE AddressId=?;");
            preparedStatement.setString(1, Address1);
            preparedStatement.setString(2, Address2);
            preparedStatement.setString(3, City);
            preparedStatement.setString(4, State);
            preparedStatement.setString(5, Zipcode);
            preparedStatement.setString(6, Country);
            preparedStatement.setString(7, Region);
            preparedStatement.setInt(8, UserId);
            preparedStatement.setInt(9, AddressId);
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
