



























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class ItemReview extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_REVIEW_ID = "ItemReviewId";
        public static final String PROP_ITEM_ID = "ItemId";
        public static final String PROP_RATING = "Rating";
        public static final String PROP_HELPFUL_YES = "HelpfulYes";
        public static final String PROP_HELPFUL_NO = "HelpfulNo";
        

        private Integer itemReviewId;
                
        private Integer itemId;
        private Item item;        
        private Integer rating;
                
        private Integer helpfulYes;
                
        private Integer helpfulNo;
                
                 
        ArrayList<Comment> commentList; 
        
        

        public ItemReview()
        {
            this.itemReviewId = 0; 
       this.itemId = 0; 
       this.rating = 0; 
       this.helpfulYes = 0; 
       this.helpfulNo = 0; 
        
       commentList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return itemReviewId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("itemReviewId", itemReviewId == null ? 0 : itemReviewId);
                
            builder.add("itemId", itemId == null ? 0 : itemId);
                
            builder.add("rating", rating == null ? 0 : rating);
                
            builder.add("helpfulYes", helpfulYes == null ? 0 : helpfulYes);
                
            builder.add("helpfulNo", helpfulNo == null ? 0 : helpfulNo);
        
        
    
     if(item != null) item.addJson(builder);
        
     
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemReview.PROP_ITEM_REVIEW_ID) || column.equals(ItemReview.PROP_ITEM_ID) || column.equals(ItemReview.PROP_RATING) || column.equals(ItemReview.PROP_HELPFUL_YES) || column.equals(ItemReview.PROP_HELPFUL_NO) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(ItemReview.PROP_ITEM_REVIEW_ID) || column.equals(ItemReview.PROP_ITEM_ID) || column.equals(ItemReview.PROP_RATING) || column.equals(ItemReview.PROP_HELPFUL_YES) || column.equals(ItemReview.PROP_HELPFUL_NO) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemReview process(ResultSet rs) throws SQLException
        {        
            return new ItemReview(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
        }
              
       public ItemReview(Integer ItemReviewId, Integer ItemId, Integer Rating, Integer HelpfulYes, Integer HelpfulNo)
       {
            this.itemReviewId = ItemReviewId;
       this.itemId = ItemId;
       this.rating = Rating;
       this.helpfulYes = HelpfulYes;
       this.helpfulNo = HelpfulNo;
              
       commentList = null; 
        
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
            
            
                   
            public Item getItem()
                {
                    return this.item;
                }

                public void setItem(Item item)
                {
                    this.item = item;
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
            
            
         
        
        
        public ArrayList<Comment> getCommentList()
            {
                return this.commentList;
            }
            
            public void setCommentList(ArrayList<Comment> commentList)
            {
                this.commentList = commentList;
            }
        
            
    }

