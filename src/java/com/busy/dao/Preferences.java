package com.busy.dao;

public class Preferences
{

    private SiteInfo siteInfo;
    private StoreInfo storeInfo;
    private Paypal paypalInfo;

    public Preferences()
    {
        siteInfo = SiteInfo.getAllSiteInfoByColumn(SiteInfo.PROP_SITEINFOID, "1").get(0);
        storeInfo = StoreInfo.getAllStoreInfoByColumn(StoreInfo.PROP_STOREINFOID, "1").get(0);
        paypalInfo = Paypal.getAllPaypalByColumn(Paypal.PROP_PAYPALID, "1").get(0);
    }

    public SiteInfo getSiteInfo()
    {
        return siteInfo;
    }

    public void setSiteInfo(SiteInfo siteInfo)
    {
        this.siteInfo = siteInfo;
    }

    public StoreInfo getStoreInfo()
    {
        return storeInfo;
    }

    public void setStoreInfo(StoreInfo storeInfo)
    {
        this.storeInfo = storeInfo;
    }

    public Paypal getPaypalInfo()
    {
        return paypalInfo;
    }

    public void setPaypalInfo(Paypal paypalInfo)
    {
        this.paypalInfo = paypalInfo;
    }
}
