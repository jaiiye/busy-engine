











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class Slider implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SLIDER_ID = "SliderId";
        public static final String PROP_SLIDER_NAME = "SliderName";
        public static final String PROP_SLIDER_TYPE_ID = "SliderTypeId";
        public static final String PROP_FORM_ID = "FormId";
        

        private Integer sliderId;
                
        private String sliderName;
                
        private Integer sliderTypeId;
        private SliderType sliderType;        
        private Integer formId;
        private Form form;        
                 
        ArrayList<Page> pageList; 
        ArrayList<SliderItem> sliderItemList; 
        
        

        public Slider()
        {
            this.sliderId = 0; 
       this.sliderName = ""; 
       this.sliderTypeId = 0; 
       this.formId = 0; 
        
       pageList = null; 
        sliderItemList = null; 
        
       }
        
        public Slider(Integer SliderId, String SliderName, Integer SliderTypeId, Integer FormId)
        {
            this.sliderId = SliderId;
       this.sliderName = SliderName;
       this.sliderTypeId = SliderTypeId;
       this.formId = FormId;
              
       pageList = null; 
        sliderItemList = null; 
        
       } 
        
             
        
            public Integer getSliderId()
            {
                return this.sliderId;
            }
            
            public void setSliderId(Integer SliderId)
            {
                this.sliderId = SliderId;
            }
            
            
        
            public String getSliderName()
            {
                return this.sliderName;
            }
            
            public void setSliderName(String SliderName)
            {
                this.sliderName = SliderName;
            }
            
            
        
            public Integer getSliderTypeId()
            {
                return this.sliderTypeId;
            }
            
            public void setSliderTypeId(Integer SliderTypeId)
            {
                this.sliderTypeId = SliderTypeId;
            }
            
            
                   
            public SliderType getSliderType()
                {
                    return this.sliderType;
                }

                public void setSliderType(SliderType sliderType)
                {
                    this.sliderType = sliderType;
                }
                   
            
        
            public Integer getFormId()
            {
                return this.formId;
            }
            
            public void setFormId(Integer FormId)
            {
                this.formId = FormId;
            }
            
            
                   
            public Form getForm()
                {
                    return this.form;
                }

                public void setForm(Form form)
                {
                    this.form = form;
                }
                   
            
         
        
        
            public ArrayList<Page> getPageList()
            {
                return this.pageList;
            }
            
            public void setPageList(ArrayList<Page> pageList)
            {
                this.pageList = pageList;
            }
        
            public ArrayList<SliderItem> getSliderItemList()
            {
                return this.sliderItemList;
            }
            
            public void setSliderItemList(ArrayList<SliderItem> sliderItemList)
            {
                this.sliderItemList = sliderItemList;
            }
        
            
    }

