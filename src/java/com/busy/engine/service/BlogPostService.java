

































package com.busy.engine.service;

import com.busy.engine.entity.BlogPost;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface BlogPostService
{
      public Result<BlogPost> find(String userName, Integer id);
      public Result<List<BlogPost>> findAll(String userName); 
      public Result<BlogPost> store(String userName, Integer blogPostId, String title, String content, String imageURL, String tags, Integer featured, Integer ratingSum, Integer voteCount, Integer commentCount, Integer postStatus, String excerpt, Date lastModified, String locale, Integer userId, Integer blogId, Integer metaTagId);
      public Result<BlogPost> remove(String userName, Integer id);
}    




