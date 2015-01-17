package com.busy.engine.domain;

import com.busy.engine.entity.Site;
import com.busy.engine.entity.Paypal;
import com.busy.engine.dao.SiteDaoImpl;
import com.busy.engine.dao.PaypalDaoImpl;
import com.busy.engine.dao.SiteEmailDaoImpl;
import com.busy.engine.entity.SiteEmail;
import java.util.ArrayList;

public class Preferences
{
    private Site siteInfo;
    private SiteEmail siteEmail;    
    private Paypal paypalInfo;

    public Preferences()
    {
        siteInfo = new SiteDaoImpl().findByColumn(Site.PROP_SITE_ID, "1", null, null).get(0);
        siteEmail = new SiteEmailDaoImpl().find(siteInfo.getSiteEmailId());
        
        ArrayList<Paypal> results = new PaypalDaoImpl().findByColumn(Paypal.PROP_PAYPAL_ID, "1", null, null);
        if(results.size() == 0)
        {
            paypalInfo = new Paypal();
        }
        else
        {
            paypalInfo = results.get(0);
        }
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

    public SiteEmail getSiteEmail()
    {
        return siteEmail;
    }

    public void setSiteEmail(SiteEmail siteEmail)
    {
        this.siteEmail = siteEmail;
    }
    
    
}
