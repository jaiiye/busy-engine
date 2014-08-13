package com.busy.engine.domain;

import com.busy.engine.entity.Site;
import com.busy.engine.entity.Paypal;
import com.busy.engine.dao.SiteDaoImpl;
import com.busy.engine.dao.PaypalDaoImpl;

public class Preferences
{
    private Site siteInfo;
    private Paypal paypalInfo;

    public Preferences()
    {
        siteInfo = new SiteDaoImpl().findByColumn(Site.PROP_SITE_ID, "1", null, null).get(0);
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

    public Paypal getPaypalInfo()
    {
        return paypalInfo;
    }

    public void setPaypalInfo(Paypal paypalInfo)
    {
        this.paypalInfo = paypalInfo;
    }
}
