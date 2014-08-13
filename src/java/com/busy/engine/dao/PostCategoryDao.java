



















package com.busy.engine.dao;

import com.busy.engine.entity.PostCategory;

public interface PostCategoryDao extends IGenericDao<PostCategory, Integer>
{
                    
      void getRelatedBlogPostCategoryList(PostCategory post_category);     
        
}
    

