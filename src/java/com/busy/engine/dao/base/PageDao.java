



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Page;

public interface PageDao extends IGenericDao<Page, Integer>
{
                    
      Page getRelatedSitePageList(Page page);     
        
}
    

