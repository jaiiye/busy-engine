package com.transitionsoft.dao;

public class ShippingMethod
{
    private String methodId;
    private String shippingRegion;
    private String shippingMethod;
    private String shippingRate;
    private String shippingAdditional;

    public ShippingMethod(String methodId, String shippingRegion, String shippingMethod, String shippingRate, String shippingAdditional)
    {
        this.methodId = methodId;
        this.shippingRegion = shippingRegion;
        this.shippingMethod = shippingMethod;
        this.shippingRate = shippingRate;
        this.shippingAdditional = shippingAdditional;
    }

    public String getMethodId()
    {
        return methodId;
    }

    public void setMethodId(String methodId)
    {
        this.methodId = methodId;
    }

    public String getShippingRegion()
    {
        return shippingRegion;
    }

    public void setShippingRegion(String shippingRegion)
    {
        this.shippingRegion = shippingRegion;
    }

    public String getShippingMethod()
    {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod)
    {
        this.shippingMethod = shippingMethod;
    }

    public String getShippingRate()
    {
        return shippingRate;
    }

    public void setShippingRate(String shippingRate)
    {
        this.shippingRate = shippingRate;
    }

    public String getShippingAdditional()
    {
        return shippingAdditional;
    }

    public void setShippingAdditional(String shippingAdditional)
    {
        this.shippingAdditional = shippingAdditional;
    }
}
