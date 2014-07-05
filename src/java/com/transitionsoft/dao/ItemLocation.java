package com.transitionsoft.dao;

import com.transitionsoft.*;
import java.util.ArrayList;

public class ItemLocation extends BasicConnection
{

    private String ItemLocationId;
    private String ItemId;
    private String AddressId;
    private String Latitude;
    private String Longitude;

    public ItemLocation()
    {
        this.ItemLocationId = "";
        this.ItemId = "";
        this.AddressId = "";
        this.Latitude = "";
        this.Longitude = "";
    }

    public ItemLocation(String ItemLocationId, String ItemId, String AddressId, String Latitude, String Longitude)
    {
        this.ItemLocationId = ItemLocationId;
        this.ItemId = ItemId;
        this.AddressId = AddressId;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    public String getItemLocationId()
    {
        return this.ItemLocationId;
    }

    public void setItemLocationId(String ItemLocationId)
    {
        this.ItemLocationId = ItemLocationId;
    }

    public String getItemId()
    {
        return this.ItemId;
    }

    public void setItemId(String ItemId)
    {
        this.ItemId = ItemId;
    }

    public String getAddressId()
    {
        return this.AddressId;
    }

    public void setAddressId(String AddressId)
    {
        this.AddressId = AddressId;
    }

    public String getLatitude()
    {
        return this.Latitude;
    }

    public void setLatitude(String Latitude)
    {
        this.Latitude = Latitude;
    }

    public String getLongitude()
    {
        return this.Longitude;
    }

    public void setLongitude(String Longitude)
    {
        this.Longitude = Longitude;
    }
}
