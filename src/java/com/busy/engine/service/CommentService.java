package com.busy.engine.service;

import com.busy.engine.entity.Comment;
import com.busy.engine.vo.Result;
import java.util.List;
import java.util.Date;

public interface CommentService
{
      public Result<Comment> find(String userName, Integer id);
      public Result<List<Comment>> findAll(String userName); 
      public Result<Comment> store(String userName, Integer commentId, String title, String content, Date date, Integer commentStatus, Integer userId, Integer blogPostId, Integer itemReviewId);
      public Result<Comment> remove(String userName, Integer id);
}    




