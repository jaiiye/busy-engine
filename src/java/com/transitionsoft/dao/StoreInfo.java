package com.transitionsoft.dao;

public class StoreInfo
{
    private int id;
    private String logoTitle;
    private String logoImage;
    private String storeName;
    private String companyName;
    private int storeLocalization;

    public StoreInfo(int id, String logoTitle, String logoImage, String storeName, String companyName, int storeLocalization)
    {
        this.id = id;
        this.logoTitle = logoTitle;
        this.logoImage = logoImage;
        this.storeName = storeName;
        this.companyName = companyName;
        this.storeLocalization = storeLocalization;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getLogoTitle()
    {
        return logoTitle;
    }

    public void setLogoTitle(String logoTitle)
    {
        this.logoTitle = logoTitle;
    }

    public String getLogoImage()
    {
        return logoImage;
    }

    public void setLogoImage(String logoImage)
    {
        this.logoImage = logoImage;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public int getStoreLocalization()
    {
        return storeLocalization;
    }

    public void setStoreLocalization(int storeLocalization)
    {
        this.storeLocalization = storeLocalization;
    }
}
