package com.busy.engine.dao;

import com.busy.engine.entity.Site;

public interface SiteDao extends IGenericDao<Site, Integer>
{

    void getRelatedTemplate(Site site);
    void getRelatedTemplateWithInfo(Site site);

    void getRelatedSiteEmail(Site site);
    void getRelatedSiteEmailWithInfo(Site site);

    void getRelatedDashboard(Site site);
    void getRelatedDashboardWithInfo(Site site);

    void getRelatedTenant(Site site);
    void getRelatedTenantWithInfo(Site site);

    void getRelatedSiteAttributeList(Site site);
    void getRelatedSiteFolderList(Site site);
    void getRelatedSiteImageList(Site site);
    void getRelatedSiteItemList(Site site);
    void getRelatedSiteLanguageList(Site site);
    void getRelatedSitePageList(Site site);
    void getRelatedUserGroupList(Site site);

}
