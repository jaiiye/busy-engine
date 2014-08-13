



















package com.busy.engine.dao;

import com.busy.engine.entity.Blog;

public interface BlogDao extends IGenericDao<Blog, Integer>
{
                    
      void getRelatedBlogPostList(Blog blog);     
                  
      void getRelatedUserServiceList(Blog blog);     
        
}
    

