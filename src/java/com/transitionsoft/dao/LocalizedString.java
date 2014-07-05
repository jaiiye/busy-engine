package com.transitionsoft.dao;

public class LocalizedString
{
    private String stringLocale;
    private String stringLocaleId;
    private String stringValue;

    public String getStringLocaleId()
    {
        return stringLocaleId;
    }

    public void setStringLocaleId(String stringLocaleId)
    {
        this.stringLocaleId = stringLocaleId;
    }
        
    public String getStringLocale()
    {
        return stringLocale;
    }

    public void setStringLocale(String stringLocale)
    {
        this.stringLocale = stringLocale;
    }

    public String getStringValue()
    {
        return stringValue;
    }

    public void setStringValue(String stringValue)
    {
        this.stringValue = stringValue;
    }


}
