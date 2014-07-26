



















package com.busy.engine.dao.base;

import com.busy.engine.domain.PostCategory;

public interface PostCategoryDao extends IGenericDao<PostCategory, Integer>
{
                    
      PostCategory getRelatedBlogPostCategoryList(PostCategory post_category);     
        
}
    

