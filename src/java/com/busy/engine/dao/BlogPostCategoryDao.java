


































package com.busy.engine.dao;

import com.busy.engine.entity.BlogPostCategory;

public interface BlogPostCategoryDao extends IGenericDao<BlogPostCategory, Integer>
{

        void getRelatedBlogPost(BlogPostCategory blog_post_category);
        void getRelatedBlogPostWithInfo(BlogPostCategory blog_post_category);        
        
        void getRelatedPostCategory(BlogPostCategory blog_post_category);
        void getRelatedPostCategoryWithInfo(BlogPostCategory blog_post_category);        
         

      
}
    
