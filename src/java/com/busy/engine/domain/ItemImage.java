











 










    package com.busy.engine.domain;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    
    public class ItemImage extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_IMAGE_ID = "ItemImageId";
        public static final String PROP_IMAGE_NAME = "ImageName";
        public static final String PROP_THUMBNAIL_NAME = "ThumbnailName";
        public static final String PROP_ALTERNATE_TEXT = "AlternateText";
        public static final String PROP_RANK = "Rank";
        public static final String PROP_ITEM_ID = "ItemId";
        

        private Integer itemImageId;
                
        private String imageName;
                
        private String thumbnailName;
                
        private String alternateText;
                
        private Integer rank;
                
        private Integer itemId;
        private Item item;        
                 
        
        

        public ItemImage()
        {
            this.itemImageId = 0; 
       this.imageName = ""; 
       this.thumbnailName = ""; 
       this.alternateText = ""; 
       this.rank = 0; 
       this.itemId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return itemImageId;
       }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemImage.PROP_ITEM_IMAGE_ID) || column.equals(ItemImage.PROP_IMAGE_NAME) || column.equals(ItemImage.PROP_THUMBNAIL_NAME) || column.equals(ItemImage.PROP_ALTERNATE_TEXT) || column.equals(ItemImage.PROP_RANK) || column.equals(ItemImage.PROP_ITEM_ID) )
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
            if (column.equals(ItemImage.PROP_ITEM_IMAGE_ID) || column.equals(ItemImage.PROP_RANK) || column.equals(ItemImage.PROP_ITEM_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemImage process(ResultSet rs) throws SQLException
        {        
            return new ItemImage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
        }
              
       public ItemImage(Integer ItemImageId, String ImageName, String ThumbnailName, String AlternateText, Integer Rank, Integer ItemId)
       {
            this.itemImageId = ItemImageId;
       this.imageName = ImageName;
       this.thumbnailName = ThumbnailName;
       this.alternateText = AlternateText;
       this.rank = Rank;
       this.itemId = ItemId;
              
       
       } 
        
             
        
            public Integer getItemImageId()
            {
                return this.itemImageId;
            }
            
            public void setItemImageId(Integer ItemImageId)
            {
                this.itemImageId = ItemImageId;
            }
            
            
        
            public String getImageName()
            {
                return this.imageName;
            }
            
            public void setImageName(String ImageName)
            {
                this.imageName = ImageName;
            }
            
            
        
            public String getThumbnailName()
            {
                return this.thumbnailName;
            }
            
            public void setThumbnailName(String ThumbnailName)
            {
                this.thumbnailName = ThumbnailName;
            }
            
            
        
            public String getAlternateText()
            {
                return this.alternateText;
            }
            
            public void setAlternateText(String AlternateText)
            {
                this.alternateText = AlternateText;
            }
            
            
        
            public Integer getRank()
            {
                return this.rank;
            }
            
            public void setRank(Integer Rank)
            {
                this.rank = Rank;
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
                   
            
         
        
        
            
    }

