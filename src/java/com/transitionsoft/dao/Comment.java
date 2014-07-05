package com.transitionsoft.dao;

import java.io.Serializable;

public class Comment implements Serializable
{

    private Integer commentId;
    private int postId;
    private String commentTitle;
    private String commentBody;
    private String commentDate;
    private int userId;

    public Comment()
    {
    }

    public Comment(Integer commentId)
    {
        this.commentId = commentId;
    }

    public Comment(Integer commentId, int postId, String commentTitle, String commentBody, String commentDate, int userId)
    {
        this.commentId = commentId;
        this.postId = postId;
        this.commentTitle = commentTitle;
        this.commentBody = commentBody;
        this.commentDate = commentDate;
        this.userId = userId;
    }

    public Integer getCommentId()
    {
        return commentId;
    }

    public void setPostCommentId(Integer postCommentId)
    {
        this.commentId = postCommentId;
    }

    public int getPostId()
    {
        return postId;
    }

    public void setPostId(int postId)
    {
        this.postId = postId;
    }

    public String getCommentTitle()
    {
        return commentTitle;
    }

    public void setCommentTitle(String postCommentTitle)
    {
        this.commentTitle = postCommentTitle;
    }

    public String getCommentBody()
    {
        return commentBody;
    }

    public void setCommentBody(String postCommentBody)
    {
        this.commentBody = postCommentBody;
    }

    public String getCommentDate()
    {
        return commentDate;
    }

    public void setCommentDate(String postCommentDate)
    {
        this.commentDate = postCommentDate;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (commentId != null ? commentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment))
        {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.commentId == null && other.commentId != null) || (this.commentId != null && !this.commentId.equals(other.commentId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.transitionsoft.Comment[commentId=" + commentId + "]";
    }

}
