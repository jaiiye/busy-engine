


































package com.busy.engine.dao;

import com.busy.engine.entity.Template;

public interface TemplateDao extends IGenericDao<Template, Integer>
{

        void getRelatedTemplateType(Template template);
        void getRelatedTemplateTypeWithInfo(Template template);        
        
        void getRelatedParentTemplate(Template template);
        void getRelatedParentTemplateWithInfo(Template template);        
         

    void getRelatedItemList(Template template);
    void getRelatedPageList(Template template);
    void getRelatedResourceUrlList(Template template);
    void getRelatedSiteList(Template template);
    void getRelatedVendorList(Template template);
      
}
    
