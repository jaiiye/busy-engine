











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class SliderItem extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SLIDER_ITEM_ID = "SliderItemId";
        public static final String PROP_TITLE = "Title";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_URL = "Url";
        public static final String PROP_IMAGE_NAME = "ImageName";
        public static final String PROP_ALTERNATE_TEXT = "AlternateText";
        public static final String PROP_RANK = "Rank";
        public static final String PROP_SLIDER_ID = "SliderId";
        

        private Integer sliderItemId;
                
        private String title;
                
        private String description;
                
        private String url;
                
        private String imageName;
                
        private String alternateText;
                
        private Integer rank;
                
        private Integer sliderId;
        private Slider slider;        
                 
        
        

        public SliderItem()
        {
            this.sliderItemId = 0; 
       this.title = ""; 
       this.description = ""; 
       this.url = ""; 
       this.imageName = ""; 
       this.alternateText = ""; 
       this.rank = 0; 
       this.sliderId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return sliderItemId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("sliderItemId", sliderItemId).add("title", title).add("description", description).add("url", url).add("imageName", imageName).add("alternateText", alternateText).add("rank", rank).add("sliderId", sliderId);
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SliderItem.PROP_SLIDER_ITEM_ID) || column.equals(SliderItem.PROP_TITLE) || column.equals(SliderItem.PROP_DESCRIPTION) || column.equals(SliderItem.PROP_URL) || column.equals(SliderItem.PROP_IMAGE_NAME) || column.equals(SliderItem.PROP_ALTERNATE_TEXT) || column.equals(SliderItem.PROP_RANK) || column.equals(SliderItem.PROP_SLIDER_ID) )
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
            if (column.equals(SliderItem.PROP_SLIDER_ITEM_ID) || column.equals(SliderItem.PROP_RANK) || column.equals(SliderItem.PROP_SLIDER_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SliderItem process(ResultSet rs) throws SQLException
        {        
            return new SliderItem(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
        }
              
       public SliderItem(Integer SliderItemId, String Title, String Description, String Url, String ImageName, String AlternateText, Integer Rank, Integer SliderId)
       {
            this.sliderItemId = SliderItemId;
       this.title = Title;
       this.description = Description;
       this.url = Url;
       this.imageName = ImageName;
       this.alternateText = AlternateText;
       this.rank = Rank;
       this.sliderId = SliderId;
              
       
       } 
        
             
        
            public Integer getSliderItemId()
            {
                return this.sliderItemId;
            }
            
            public void setSliderItemId(Integer SliderItemId)
            {
                this.sliderItemId = SliderItemId;
            }
            
            
        
            public String getTitle()
            {
                return this.title;
            }
            
            public void setTitle(String Title)
            {
                this.title = Title;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
        
            public String getUrl()
            {
                return this.url;
            }
            
            public void setUrl(String Url)
            {
                this.url = Url;
            }
            
            
        
            public String getImageName()
            {
                return this.imageName;
            }
            
            public void setImageName(String ImageName)
            {
                this.imageName = ImageName;
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
            
            
        
            public Integer getSliderId()
            {
                return this.sliderId;
            }
            
            public void setSliderId(Integer SliderId)
            {
                this.sliderId = SliderId;
            }
            
            
                   
            public Slider getSlider()
                {
                    return this.slider;
                }

                public void setSlider(Slider slider)
                {
                    this.slider = slider;
                }
                   
            
         
        
        
            
    }

