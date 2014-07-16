package com.busy.dao;

import com.busy.entity.*;

public class Preferences
{
    private Site siteInfo;
    private StoreInfo storeInfo;
    private Paypal paypalInfo;

    public Preferences()
    {
        siteInfo = SiteDAO.getAllSiteByColumn(Site.PROP_SITE_ID, "1").get(0);
        storeInfo = StoreInfoDAO.getAllStoreInfoByColumn(StoreInfo.PROP_STORE_INFO_ID, "1").get(0);
        paypalInfo = PaypalDAO.getAllPaypalByColumn(Paypal.PROP_PAYPAL_ID, "1").get(0);
    }

    public Site getSiteInfo()
    {
        return siteInfo;
    }

    public void setSiteInfo(Site siteInfo)
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
