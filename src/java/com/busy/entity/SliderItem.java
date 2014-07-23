











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class SliderItem implements Serializable
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

