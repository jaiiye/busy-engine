


































package com.busy.engine.dao;

import com.busy.engine.entity.BlogPost;

public interface BlogPostDao extends IGenericDao<BlogPost, Integer>
{

        void getRelatedUser(BlogPost blog_post);
        void getRelatedUserWithInfo(BlogPost blog_post);        
        
        void getRelatedBlog(BlogPost blog_post);
        void getRelatedBlogWithInfo(BlogPost blog_post);        
        
        void getRelatedMetaTag(BlogPost blog_post);
        void getRelatedMetaTagWithInfo(BlogPost blog_post);        
         

    void getRelatedBlogPostCategoryList(BlogPost blog_post);
    void getRelatedCommentList(BlogPost blog_post);
      
}
    
