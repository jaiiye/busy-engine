


































package com.busy.engine.dao;

import com.busy.engine.entity.BlogType;

public interface BlogTypeDao extends IGenericDao<BlogType, Integer>
{
                    
      void getRelatedBlogList(BlogType blog_type);     
        
}
    

