



















package com.busy.engine.dao.base;

import com.busy.engine.domain.BlogType;

public interface BlogTypeDao extends IGenericDao<BlogType, Integer>
{
                    
      BlogType getRelatedBlogList(BlogType blog_type);     
        
}
    

