


































package com.busy.engine.dao;

import com.busy.engine.entity.Page;

public interface PageDao extends IGenericDao<Page, Integer>
{

        void getRelatedForm(Page page);
        void getRelatedFormWithInfo(Page page);        
        
        void getRelatedSlider(Page page);
        void getRelatedSliderWithInfo(Page page);        
        
        void getRelatedMetaTag(Page page);
        void getRelatedMetaTagWithInfo(Page page);        
        
        void getRelatedTemplate(Page page);
        void getRelatedTemplateWithInfo(Page page);        
         

    void getRelatedSitePageList(Page page);
      
}
    
