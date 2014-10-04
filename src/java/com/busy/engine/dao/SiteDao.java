


































package com.busy.engine.dao;

import com.busy.engine.entity.Site;

public interface SiteDao extends IGenericDao<Site, Integer>
{
                    
      void getRelatedSiteAttributeList(Site site);     
                  
      void getRelatedSiteFolderList(Site site);     
                  
      void getRelatedSiteImageList(Site site);     
                  
      void getRelatedSiteItemList(Site site);     
                  
      void getRelatedSiteLanguageList(Site site);     
                  
      void getRelatedSitePageList(Site site);     
                  
      void getRelatedUserGroupList(Site site);     
        
}
    

