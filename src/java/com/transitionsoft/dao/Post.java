package com.transitionsoft.dao;

import java.io.Serializable;
import java.util.ArrayList;



public class Post implements Serializable
{    
    private Integer postId;    
    private String postUserName;
    private String postTitle;
    private String postBody;
    private String postPicURL;
    private String postDate;
    private String postTags;
    private int postFeatured;
    private int userId;
    private double postRating;
    private ArrayList<Comment> comments;


    public Post()
    {
        comments = new ArrayList<Comment>();
    }

    public Post(Integer postId)
    {
        this.postId = postId;
    }

    public Post(Integer postId, String postTitle, String postBody, String postPicURL, String postDate, String postTags, int userId, double postRating, String postUserName )
    {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postDate = postDate;
        this.postPicURL = postPicURL;
        this.userId = userId;
        this.postUserName = postUserName;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postRating = postRating;
    }

    public Integer getPostId()
    {
        return postId;
    }

    public void setPostId(Integer postId)
    {
        this.postId = postId;
    }

    public String getPostTitle()
    {
        return postTitle;
    }

    public void setPostTitle(String postTitle)
    {
        this.postTitle = postTitle;
    }

    public String getPostBody()
    {
        return postBody;
    }

    public void setPostBody(String postBody)
    {
        this.postBody = postBody;
    }

    public String getPostPicURL()
    {
        return postPicURL;
    }

    public void setPostPicURL(String postPicURL)
    {
        this.postPicURL = postPicURL;
    }

    public String getPostDate()
    {
        return postDate;
    }

    public void setPostDate(String postDate)
    {
        this.postDate = postDate;
    }

    public String getPostTags()
    {
        return postTags;
    }

    public void setPostTags(String postTags)
    {
        this.postTags = postTags;
    }

    public int getPostFeatured()
    {
        return postFeatured;
    }

    public void setPostFeatured(int postFeatured)
    {
        this.postFeatured = postFeatured;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public double getPostRating()
    {
        return postRating;
    }

    public void setPostRating(double postRating)
    {
        this.postRating = postRating;
    }

    public String getPostUserName()
    {
        return postUserName;
    }

    public void setPostUserName(String postUserName)
    {
        this.postUserName = postUserName;
    }

    public ArrayList<Comment> getComments()
    {
        return comments;
    }

    public void addComment(Comment comment)
    {
        comments.add(comment);
    }

    public void setComments(ArrayList<Comment> comments)
    {
        this.comments = comments;
    }

    public Comment getComment(int index)
    {
        return this.comments.get(index);
    }


    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (postId != null ? postId.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof Post))
        {
            return false;
        }

        Post other = (Post) object;
        if ((this.postId == null && other.postId != null) || (this.postId != null && !this.postId.equals(other.postId)))
        {
            return false;
        }

        return true;
    }


    @Override
    public String toString()
    {
        return "com.transitionsoft.BlogPosts[postId=" + postId + "]";
    }
}
