



















package com.busy.engine.dao;

import com.busy.engine.entity.MetaTag;

public interface MetaTagDao extends IGenericDao<MetaTag, Integer>
{
                    
      void getRelatedBlogPostList(MetaTag meta_tag);     
                  
      void getRelatedItemList(MetaTag meta_tag);     
                  
      void getRelatedPageList(MetaTag meta_tag);     
                  
      void getRelatedVendorList(MetaTag meta_tag);     
        
}
    

