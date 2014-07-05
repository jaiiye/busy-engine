package com.transitionsoft.dao;

import com.transitionsoft.*;

public class ItemReview extends BasicConnection
{

    private String ItemReviewId;
    private String ItemId;
    private String CommentId;
    private String Rating;

    public ItemReview()
    {
        this.ItemReviewId = "";
        this.ItemId = "";
        this.CommentId = "";
        this.Rating = "";
    }

    public ItemReview(String ItemReviewId, String ItemId, String CommentId, String Rating)
    {
        this.ItemReviewId = ItemReviewId;
        this.ItemId = ItemId;
        this.CommentId = CommentId;
        this.Rating = Rating;
    }

    public String getItemReviewId()
    {
        return this.ItemReviewId;
    }

    public void setItemReviewId(String ItemReviewId)
    {
        this.ItemReviewId = ItemReviewId;
    }

    public String getItemId()
    {
        return this.ItemId;
    }

    public void setItemId(String ItemId)
    {
        this.ItemId = ItemId;
    }

    public String getCommentId()
    {
        return this.CommentId;
    }

    public void setCommentId(String CommentId)
    {
        this.CommentId = CommentId;
    }

    public String getRating()
    {
        return this.Rating;
    }

    public void setRating(String Rating)
    {
        this.Rating = Rating;
    }

}
