


































package com.busy.engine.dao;

import com.busy.engine.entity.Comment;

public interface CommentDao extends IGenericDao<Comment, Integer>
{

        void getRelatedUser(Comment comment);
        void getRelatedUserWithInfo(Comment comment);        
        
        void getRelatedBlogPost(Comment comment);
        void getRelatedBlogPostWithInfo(Comment comment);        
        
        void getRelatedItemReview(Comment comment);
        void getRelatedItemReviewWithInfo(Comment comment);        
         

      
}
    
