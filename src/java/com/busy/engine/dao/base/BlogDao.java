



















package com.busy.engine.dao.base;

import com.busy.engine.domain.Blog;

public interface BlogDao extends IGenericDao<Blog, Integer>
{
                    
      Blog getRelatedBlogPostList(Blog blog);     
                  
      Blog getRelatedUserServiceList(Blog blog);     
        
}
    

