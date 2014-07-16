


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class SliderType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SLIDER_TYPE_ID = "SliderTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_CODE = "Code";
        

        private Integer sliderTypeId;
        private String typeName;
        private String code;
        

        public SliderType()
        {
            this.sliderTypeId = 0; 
       this.typeName = ""; 
       this.code = ""; 
        }
        
        public SliderType(Integer SliderTypeId, String TypeName, String Code)
        {
            this.sliderTypeId = SliderTypeId;
       this.typeName = TypeName;
       this.code = Code;
        } 
        
             
        
            public Integer getSliderTypeId()
            {
                return this.sliderTypeId;
            }
            
            public void setSliderTypeId(Integer SliderTypeId)
            {
                this.sliderTypeId = SliderTypeId;
            }
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
        
            public String getCode()
            {
                return this.code;
            }
            
            public void setCode(String Code)
            {
                this.code = Code;
            }
           
            
    }

