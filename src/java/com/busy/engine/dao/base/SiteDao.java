



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Site;

public interface SiteDao extends IGenericDao<Site, Integer>
{
                    
      Site getRelatedSiteAttributeList(Site site);     
                  
      Site getRelatedSiteFolderList(Site site);     
                  
      Site getRelatedSiteImageList(Site site);     
                  
      Site getRelatedSiteItemList(Site site);     
                  
      Site getRelatedSiteLanguageList(Site site);     
                  
      Site getRelatedSitePageList(Site site);     
                  
      Site getRelatedUserGroupList(Site site);     
        
}
    

