



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Template;

public interface TemplateDao extends IGenericDao<Template, Integer>
{
                    
      Template getRelatedItemList(Template template);     
                  
      Template getRelatedPageList(Template template);     
                  
      Template getRelatedResourceUrlList(Template template);     
                  
      Template getRelatedSiteList(Template template);     
                  
      Template getRelatedVendorList(Template template);     
        
}
    

