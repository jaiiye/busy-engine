


































package com.busy.engine.dao;

import com.busy.engine.entity.Blog;

public interface BlogDao extends IGenericDao<Blog, Integer>
{

        void getRelatedBlogType(Blog blog);
        void getRelatedBlogTypeWithInfo(Blog blog);        
        
        void getRelatedKnowledgeBase(Blog blog);
        void getRelatedKnowledgeBaseWithInfo(Blog blog);        
         

    void getRelatedBlogPostList(Blog blog);
    void getRelatedUserServiceList(Blog blog);
      
}
    
