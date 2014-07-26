package com.busy.engine;

import com.busy.engine.domain.Site;
import com.busy.engine.domain.StoreInfo;
import com.busy.engine.domain.Paypal;
import com.busy.engine.dao.impl.SiteDaoImpl;
import com.busy.engine.dao.impl.StoreInfoDaoImpl;
import com.busy.engine.dao.impl.PaypalDaoImpl;

public class Preferences
{
    private Site siteInfo;
    private StoreInfo storeInfo;
    private Paypal paypalInfo;

    public Preferences()
    {
        siteInfo = new SiteDaoImpl().findByColumn(Site.PROP_SITE_ID, "1", null, null).get(0);
        storeInfo = new StoreInfoDaoImpl().findByColumn(StoreInfo.PROP_STORE_INFO_ID, "1", null, null).get(0);
        paypalInfo = new PaypalDaoImpl().findByColumn(Paypal.PROP_PAYPAL_ID, "1", null, null).get(0);
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
