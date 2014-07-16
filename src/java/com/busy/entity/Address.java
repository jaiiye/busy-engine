package com.busy.entity;

import java.io.Serializable;

public class Address implements Serializable
{
    private static final long serialVersionUID = 1L;
    public static final String PROP_ADDRESS_ID = "AddressId";
    public static final String PROP_RECIPIENT = "Recipient";
    public static final String PROP_ADDRESS1 = "Address1";
    public static final String PROP_ADDRESS2 = "Address2";
    public static final String PROP_CITY = "City";
    public static final String PROP_STATE_PROVINCE = "StateProvince";
    public static final String PROP_ZIP_POSTAL_CODE = "ZipPostalCode";
    public static final String PROP_COUNTRY = "Country";
    public static final String PROP_REGION = "Region";
    public static final String PROP_STATUS = "Status";
    public static final String PROP_LOCALE = "Locale";

    private Integer addressId;
    private String recipient;
    private String address1;
    private String address2;
    private String city;
    private String stateProvince;
    private String zipPostalCode;
    private String country;
    private String region;
    private Integer status;
    private String locale;

    public Address()
    {
        this.addressId = 0;
        this.recipient = "";
        this.address1 = "";
        this.address2 = "";
        this.city = "";
        this.stateProvince = "";
        this.zipPostalCode = "";
        this.country = "";
        this.region = "";
        this.status = 0;
        this.locale = "";
    }

    public Address(Integer AddressId, String Recipient, String Address1, String Address2, String City, String StateProvince, String ZipPostalCode, String Country, String Region, Integer Status, String Locale)
    {
        this.addressId = AddressId;
        this.recipient = Recipient;
        this.address1 = Address1;
        this.address2 = Address2;
        this.city = City;
        this.stateProvince = StateProvince;
        this.zipPostalCode = ZipPostalCode;
        this.country = Country;
        this.region = Region;
        this.status = Status;
        this.locale = Locale;
    }

    public Integer getAddressId()
    {
        return this.addressId;
    }

    public void setAddressId(Integer AddressId)
    {
        this.addressId = AddressId;
    }

    public String getRecipient()
    {
        return this.recipient;
    }

    public void setRecipient(String Recipient)
    {
        this.recipient = Recipient;
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

    public String getStateProvince()
    {
        return this.stateProvince;
    }

    public void setStateProvince(String StateProvince)
    {
        this.stateProvince = StateProvince;
    }

    public String getZipPostalCode()
    {
        return this.zipPostalCode;
    }

    public void setZipPostalCode(String ZipPostalCode)
    {
        this.zipPostalCode = ZipPostalCode;
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

    public Integer getStatus()
    {
        return this.status;
    }

    public void setStatus(Integer Status)
    {
        this.status = Status;
    }

    public String getLocale()
    {
        return this.locale;
    }

    public void setLocale(String Locale)
    {
        this.locale = Locale;
    }

}
