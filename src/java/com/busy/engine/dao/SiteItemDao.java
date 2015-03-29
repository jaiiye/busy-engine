


































package com.busy.engine.dao;

import com.busy.engine.entity.SiteItem;

public interface SiteItemDao extends IGenericDao<SiteItem, Integer>
{

        void getRelatedSite(SiteItem site_item);
        void getRelatedSiteWithInfo(SiteItem site_item);        
        
        void getRelatedItem(SiteItem site_item);
        void getRelatedItemWithInfo(SiteItem site_item);        
         

      
}
    
