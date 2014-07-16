


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
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
        private Integer formId;
        

        public Slider()
        {
            this.sliderId = 0; 
       this.sliderName = ""; 
       this.sliderTypeId = 0; 
       this.formId = 0; 
        }
        
        public Slider(Integer SliderId, String SliderName, Integer SliderTypeId, Integer FormId)
        {
            this.sliderId = SliderId;
       this.sliderName = SliderName;
       this.sliderTypeId = SliderTypeId;
       this.formId = FormId;
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
        
            public Integer getFormId()
            {
                return this.formId;
            }
            
            public void setFormId(Integer FormId)
            {
                this.formId = FormId;
            }
           
            
    }

