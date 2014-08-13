



















package com.busy.engine.dao;

import com.busy.engine.entity.Page;

public interface PageDao extends IGenericDao<Page, Integer>
{
                    
      void getRelatedSitePageList(Page page);     
        
}
    

