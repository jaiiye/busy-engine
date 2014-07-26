



















package com.busy.engine.dao.base;

import com.busy.engine.domain.BlogPost;

public interface BlogPostDao extends IGenericDao<BlogPost, Integer>
{
                    
      BlogPost getRelatedBlogPostCategoryList(BlogPost blog_post);     
                  
      BlogPost getRelatedCommentList(BlogPost blog_post);     
        
}
    

