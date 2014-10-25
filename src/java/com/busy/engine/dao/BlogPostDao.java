package com.busy.engine.dao;

import com.busy.engine.entity.BlogPost;

public interface BlogPostDao extends IGenericDao<BlogPost, Integer>
{

    void getRelatedBlogPostCategoryList(BlogPost blog_post);

    void getRelatedCommentList(BlogPost blog_post);

}
