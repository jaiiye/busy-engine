


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class ItemReview implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_REVIEW_ID = "ItemReviewId";
        public static final String PROP_ITEM_ID = "ItemId";
        public static final String PROP_RATING = "Rating";
        public static final String PROP_HELPFUL_YES = "HelpfulYes";
        public static final String PROP_HELPFUL_NO = "HelpfulNo";
        

        private Integer itemReviewId;
        private Integer itemId;
        private Integer rating;
        private Integer helpfulYes;
        private Integer helpfulNo;
        

        public ItemReview()
        {
            this.itemReviewId = 0; 
       this.itemId = 0; 
       this.rating = 0; 
       this.helpfulYes = 0; 
       this.helpfulNo = 0; 
        }
        
        public ItemReview(Integer ItemReviewId, Integer ItemId, Integer Rating, Integer HelpfulYes, Integer HelpfulNo)
        {
            this.itemReviewId = ItemReviewId;
       this.itemId = ItemId;
       this.rating = Rating;
       this.helpfulYes = HelpfulYes;
       this.helpfulNo = HelpfulNo;
        } 
        
             
        
            public Integer getItemReviewId()
            {
                return this.itemReviewId;
            }
            
            public void setItemReviewId(Integer ItemReviewId)
            {
                this.itemReviewId = ItemReviewId;
            }
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
        
            public Integer getRating()
            {
                return this.rating;
            }
            
            public void setRating(Integer Rating)
            {
                this.rating = Rating;
            }
        
            public Integer getHelpfulYes()
            {
                return this.helpfulYes;
            }
            
            public void setHelpfulYes(Integer HelpfulYes)
            {
                this.helpfulYes = HelpfulYes;
            }
        
            public Integer getHelpfulNo()
            {
                return this.helpfulNo;
            }
            
            public void setHelpfulNo(Integer HelpfulNo)
            {
                this.helpfulNo = HelpfulNo;
            }
           
            
    }

