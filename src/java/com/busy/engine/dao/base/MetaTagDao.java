



















package com.busy.engine.dao.base;

import com.busy.engine.domain.MetaTag;

public interface MetaTagDao extends IGenericDao<MetaTag, Integer>
{
                    
      MetaTag getRelatedBlogPostList(MetaTag meta_tag);     
                  
      MetaTag getRelatedItemList(MetaTag meta_tag);     
                  
      MetaTag getRelatedPageList(MetaTag meta_tag);     
                  
      MetaTag getRelatedVendorList(MetaTag meta_tag);     
        
}
    

